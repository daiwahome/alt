package service

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.Activity
import com.github.daiwahome.alt.model.ActivityType
import com.github.daiwahome.alt.model.ActivityWithTimestamp
import com.github.daiwahome.alt.model.Location
import com.github.daiwahome.alt.service.FilterService
import com.soywiz.klock.DateTime
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class FilterServiceTest {

    private val sut = FilterService()

    @Nested
    inner class FilterLocationsWithCircle {

        @Test
        fun filterLocationsWithCircle() {
            // setup
            val locations = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 35.676793,
                    longitude = 139.73727,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 35.672124,
                    longitude = 139.741398,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 35.670123,
                    longitude = 139.750074,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 35.686153,
                    longitude = 139.730211,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                )
            )
            val latitude = 35.676793
            val longitude = 139.73727
            val distance = 0.64

            val expected = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 35.676793,
                    longitude = 139.73727,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 35.672124,
                    longitude = 139.741398,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                )
            )

            // exercise
            val actual = sut.filterLocationsWithCircle(locations, latitude, longitude, distance)

            // verify
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Nested
    inner class FilterLocationsWithTimestamp {

        @Test
        fun filterLocationsWithTimestamp() {
            // setup
            val locations = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 2
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 3
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 4
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                )
            )
            val start = DateTime(
                year = 2019,
                month = 1,
                day = 2
            )
            val end = DateTime(
                year = 2019,
                month = 1,
                day = 3
            )

            val expected = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 2
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 3
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                )
            )

            // exercise
            val actual = sut.filterLocationsWithTimestamp(locations, start, end)

            // verify
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `locations should contain all past data when start is null`() {
            // setup
            val locations = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 2
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 3
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 4
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                )
            )
            val start = null
            val end = DateTime(
                year = 2019,
                month = 1,
                day = 3
            )

            val expected = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 2
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 3
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                )
            )

            // exercise
            val actual = sut.filterLocationsWithTimestamp(locations, start, end)

            // verify
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun `locations should contain all data since specified date time when end is null`() {
            // setup
            val locations = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 2
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 3
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 4
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                )
            )
            val start = DateTime(
                year = 2019,
                month = 1,
                day = 2
            )
            val end = null

            val expected = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 2
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 3
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 4
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = emptyList()
                )
            )

            // exercise
            val actual = sut.filterLocationsWithTimestamp(locations, start, end)

            // verify
            assertThat(actual).isEqualTo(expected)
        }
    }

    @Nested
    inner class FilterLocationsWithActivityTypes {

        @Test
        fun filterLocationsWithActivityTypes() {
            // setup
            val locations = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = listOf(
                        ActivityWithTimestamp(
                            timestamp = DateTime(
                                year = 2019,
                                month = 1,
                                day = 1
                            ),
                            activities = listOf(
                                Activity(
                                    type = ActivityType.STILL,
                                    confidence = 100
                                )
                            )
                        )
                    )
                ),
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 2
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = listOf(
                        ActivityWithTimestamp(
                            timestamp = DateTime(
                                year = 2019,
                                month = 1,
                                day = 1
                            ),
                            activities = listOf(
                                Activity(
                                    type = ActivityType.UNKNOWN,
                                    confidence = 100
                                )
                            )
                        )
                    )
                )
            )
            val types = listOf(ActivityType.STILL)

            val expected = listOf(
                Location(
                    timestamp = DateTime(
                        year = 2019,
                        month = 1,
                        day = 1
                    ),
                    latitude = 123.45,
                    longitude = 123.45,
                    accuracy = 1,
                    velocity = null,
                    heading = null,
                    altitude = null,
                    verticalAccuracy = null,
                    activities = listOf(
                        ActivityWithTimestamp(
                            timestamp = DateTime(
                                year = 2019,
                                month = 1,
                                day = 1
                            ),
                            activities = listOf(
                                Activity(
                                    type = ActivityType.STILL,
                                    confidence = 100
                                )
                            )
                        )
                    )
                )
            )

            // exercise
            val actual = sut.filterLocationsWithActivityTypes(locations, types)

            // verify
            assertThat(actual).isEqualTo(expected)
        }
    }
}