package com.github.daiwahome.alt.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.transformValues
import com.github.ajalt.clikt.parameters.options.triple
import com.github.ajalt.clikt.parameters.options.validate
import com.github.ajalt.clikt.parameters.types.double
import com.github.ajalt.clikt.parameters.types.file
import com.github.daiwahome.alt.model.ActivityType
import com.github.daiwahome.alt.model.LocationHistory
import com.github.daiwahome.alt.model.ReverseGeocodingResponse
import com.github.daiwahome.alt.service.FilterService
import com.github.daiwahome.alt.service.GoogleApiService
import com.soywiz.klock.DateTime
import kotlinx.serialization.json.Json
import java.io.File

@kotlinx.serialization.UnstableDefault
class AltCli(
    private val googleApiService: GoogleApiService,
    private val filterService: FilterService
) : CliktCommand(
    help = "Find addresses with Google maps API and Timeline"
) {

    // Options
    private val apiKey: String? by option("--api-key", envvar = "GOOGLE_MAP_API_KEY")

    private val circle: Triple<Double, Double, Double>? by option("--circle", help = "lat long dist")
        .double()
        .triple()
        .validate { it.first in -90.0..90.0 && it.second in -180.0..180.0 }

    private val timeRange: Pair<DateTime, DateTime>? by option("--time", help = "")
        .transformValues(2) {
            Pair(
                DateTime.parse(it[0]).local,
                DateTime.parse(it[1]).local
            )
        }

    private val types: List<String> by option("--type", help = "")
        .multiple()
        .validate { types -> types.all { type -> ActivityType.values().map { it.toString() }.contains(type) } }

    // Arguments
    private val path: File by argument().file(
        exists = true,
        folderOkay = false,
        readable = true
    )

    override fun run() {
        if (apiKey == null) {
            error("Should set GOOGLE_MAP_API_KEY or use --api-key option.")
        }

        val history = Json.parse(LocationHistory.serializer(), path.readText())

        val locations: List<Pair<Double, Double>> = history.locations.let { locations ->
            var candidates = locations
            circle?.let {
                candidates = filterService.filterLocationsWithCircle(
                    candidates,
                    it.first,
                    it.second,
                    it.third
                )
            }
            timeRange?.let {
                candidates = filterService.filterLocationsWithTimestamp(
                    candidates,
                    it.first,
                    it.second
                )
            }
            if (types.isNotEmpty()) {
                candidates = filterService.filterLocationsWithActivityTypes(
                    candidates,
                    types.map { ActivityType.valueOf(it) }
                )
            }
            candidates.map { location -> Pair(location.latitude, location.longitude) }
        }

        val responses: List<ReverseGeocodingResponse> = locations.map {
            googleApiService.requestReverseGeocoding(it.first, it.second, apiKey!!)
        }

        responses.flatMap { response ->
            response.results.flatMap { result ->
                result.addressComponents.map { it.longName }
            }
        }.let {
            echo(it.joinToString("\n"))
        }
    }
}