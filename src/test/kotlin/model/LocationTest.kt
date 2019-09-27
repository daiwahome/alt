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
            val sut = """
                {
                  "timestampMs": "1546300800000",
                  "latitudeE7": 12345000000,
                  "longitudeE7": 12345000000,
                  "accuracy": 12,
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
                accuracy = 12,
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
        fun `activities should be empty when the attribute is missing`() {
            val sut = """
                {
                  "timestampMs": "1546300800000",
                  "latitudeE7": 12345000000,
                  "longitudeE7": 12345000000,
                  "accuracy": 12
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
                accuracy = 12,
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
                accuracy = 12,
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
            val expected = """
                {
                  "timestampMs": "1546300800000",
                  "latitudeE7": 12345000000,
                  "longitudeE7": 12345000000,
                  "accuracy": 12,
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
        fun `activity should not be contained when the property is empty`() {
            val sut = Location(
                timestamp = DateTime(
                    year = 2019,
                    month = 1,
                    day = 1
                ),
                latitude = 123.45,
                longitude = 123.45,
                accuracy = 12,
                activities = emptyList()
            )
            val expected = """
                {
                  "timestampMs": "1546300800000",
                  "latitudeE7": 12345000000,
                  "longitudeE7": 12345000000,
                  "accuracy": 12
                }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(Location.serializer(), sut)).isEqualTo(expected)
        }
    }
}