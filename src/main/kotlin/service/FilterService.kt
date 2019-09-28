package com.github.daiwahome.alt.service

import com.github.daiwahome.alt.model.ActivityType
import com.github.daiwahome.alt.model.Location
import com.soywiz.klock.DateTime
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class FilterService {

    companion object {

        private const val EQUATORIAL_RADIUS = 6378.1366
    }

    private fun Double.toRadian(): Double = this * PI / 180.0

    /**
     * Filter locations with latitude and longitude and a distance form center.
     *
     * @param locations locations is filtered with the circle
     * @param latitude latitude of center of the circle
     * @param longitude latitude of center of the circle
     * @param distance a radius of the circle (km)
     */
    fun filterLocationsWithCircle(
        locations: List<Location>,
        latitude: Double,
        longitude: Double,
        distance: Double
    ): List<Location> {
        val x0 = longitude.toRadian()
        val y0 = latitude.toRadian()

        return locations.filter {
            val x = it.longitude.toRadian()
            val y = it.latitude.toRadian()
            EQUATORIAL_RADIUS * acos(sin(y)*sin(y0) + cos(y)* cos(y0)*cos(x - x0)) <= distance
        }
    }

    /**
     * Filter locations with time range.
     *
     * @param locations locations is filtered with the circle
     * @param start start date time
     * @param end end date time
     */
    fun filterLocationsWithTimestamp(
        locations: List<Location>,
        start: DateTime?,
        end: DateTime?
    ): List<Location> {
        val startDateTime = start ?: DateTime.Companion.fromUnix(0L)
        val endDateTime = end ?: DateTime.now()
        return locations.filter { it.timestamp in startDateTime..endDateTime }
    }

    /**
     * Filter locations with types.
     *
     * @param locations locations is filtered with the circle
     * @param types activity types
     */
    fun filterLocationsWithActivityTypes(
        locations: List<Location>,
        types: List<ActivityType>
    ): List<Location> {
        return locations.filter { location ->
            location.activities.any { activity ->
                activity.activities
                    .map { it.type }
                    .any { types.contains(it) }
            }
        }
    }
}