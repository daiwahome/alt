package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.Activity
import com.github.daiwahome.alt.model.ActivityType
import com.github.daiwahome.alt.model.ActivityWithTimestamp
import com.github.daiwahome.alt.model.Location
import com.github.daiwahome.alt.model.LocationHistory
import com.soywiz.klock.DateTime
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class LocationHistoryTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            //language=JSON
            val sut = """
                {
                  "locations": [
                    {
                      "timestampMs": "1546300800000",
                      "latitudeE7": 12345000000,
                      "longitudeE7": 12345000000,
                      "accuracy": 1,
                      "velocity": 2,
                      "heading": 3,
                      "altitude": 4,
                      "verticalAccuracy": 5,
                      "activity": [
                        {
                          "timestampMs": "1546300800000",
                          "activity": [
                            {
                              "type": "STILL",
                              "confidence": 100
                            }
                          ]
                        }
                      ]
                    }
                  ]
                }
            """
            val expected = LocationHistory(
                locations = listOf(
                    Location(
                        timestamp = DateTime(
                            year = 2019,
                            month = 1,
                            day = 1
                        ),
                        latitude = 123.45,
                        longitude = 123.45,
                        accuracy = 1,
                        velocity = 2,
                        heading = 3,
                        altitude = 4,
                        verticalAccuracy = 5,
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
            )
            assertThat(Json.parse(LocationHistory.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = LocationHistory(
                locations = listOf(
                    Location(
                        timestamp = DateTime(
                            year = 2019,
                            month = 1,
                            day = 1
                        ),
                        latitude = 123.45,
                        longitude = 123.45,
                        accuracy = 1,
                        velocity = 2,
                        heading = 3,
                        altitude = 4,
                        verticalAccuracy = 5,
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
            )
            //language=JSON
            val expected = """
                {
                  "locations": [
                    {
                      "timestampMs": "1546300800000",
                      "latitudeE7": 12345000000,
                      "longitudeE7": 12345000000,
                      "accuracy": 1,
                      "velocity": 2,
                      "heading": 3,
                      "altitude": 4,
                      "verticalAccuracy": 5,
                      "activity": [
                        {
                          "timestampMs": "1546300800000",
                          "activity": [
                            {
                              "type": "STILL",
                              "confidence": 100
                            }
                          ]
                        }
                      ]
                    }
                  ]
                }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(LocationHistory.serializer(), sut)).isEqualTo(expected)
        }
    }
}