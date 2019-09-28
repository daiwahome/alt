package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.Activity
import com.github.daiwahome.alt.model.ActivityType
import com.github.daiwahome.alt.model.ActivityWithTimestamp
import com.github.daiwahome.alt.model.Location
import com.soywiz.klock.DateTime
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class LocationTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            //language=JSON
            val sut = """
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
            """
            val expected = Location(
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
            assertThat(Json.parse(Location.serializer(), sut)).isEqualTo(expected)
        }

        @Test
        fun `some properties should be null or empty when attributes are missing`() {
            //language=JSON
            val sut = """
                {
                  "timestampMs": "1546300800000",
                  "latitudeE7": 12345000000,
                  "longitudeE7": 12345000000,
                  "accuracy": 1
                }
            """
            val expected = Location(
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
            )
            assertThat(Json.parse(Location.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = Location(
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
            //language=JSON
            val expected = """
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
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(Location.serializer(), sut)).isEqualTo(expected)
        }

        @Test
        fun `some attributes should not be contained when property are null or empty`() {
            val sut = Location(
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
            )
            val expected = """
                {
                  "timestampMs": "1546300800000",
                  "latitudeE7": 12345000000,
                  "longitudeE7": 12345000000,
                  "accuracy": 1
                }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(Location.serializer(), sut)).isEqualTo(expected)
        }
    }
}