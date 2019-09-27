package com.github.daiwahome.alt.service

import com.github.daiwahome.alt.model.ActivityType
import com.github.daiwahome.alt.model.Location
import com.soywiz.klock.DateTime
import kotlin.math.hypot
import kotlin.math.sqrt

class FilterService {

    /**
     * Filter locations with latitude and longitude and a distance form center.
     *
     * @param locations locations is filtered with the circle
     * @param latitude latitude of center of the circle
     * @param longitude latitude of center of the circle
     * @param distance a radius of the circle
     */
    fun filterLocationsWithCircle(
        locations: List<Location>,
        latitude: Double,
        longitude: Double,
        distance: Double
    ): List<Location> =
        locations.filter { sqrt(hypot(it.latitude - latitude, it.longitude - longitude)) <= distance }

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