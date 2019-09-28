package com.github.daiwahome.alt.service

import com.github.daiwahome.alt.model.ReverseGeocodingResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import kotlinx.coroutines.runBlocking

class GoogleApiService(
    private val client: HttpClient
) {

    companion object {

        private const val BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json"
    }

    /**
     * Obtain addresses from Google maps API.
     *
     * @param latitude
     * @param longitude
     * @return response
     */
    fun requestReverseGeocoding(
        latitude: Double,
        longitude: Double,
        apiKey: String
    ): ReverseGeocodingResponse = runBlocking {
        val url = URLBuilder(BASE_URL).apply {
            parameters.apply {
                append("latlng", "$latitude,$longitude")
                append("key", apiKey)
            }
        }.build()
        client.get<ReverseGeocodingResponse>(url)
    }
}