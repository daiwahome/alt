package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.Activity
import com.github.daiwahome.alt.model.ActivityType
import com.github.daiwahome.alt.model.ActivityWithTimestamp
import com.soywiz.klock.DateTime
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class ActivityWithTimestampTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            val sut = """
                {
                  "timestampMs": "1546300800000",
                  "activity": [
                    {
                      "type": "STILL",
                      "confidence": 100
                    }
                  ]
                }
            """
            val expected = ActivityWithTimestamp(
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
            assertThat(Json.parse(ActivityWithTimestamp.serializer(), sut)).isEqualTo(expected)
        }

        @Test
        fun `activity should be empty when the attribute is missing`() {
            val sut = """
                {
                  "timestampMs": "1546300800000"
                }
            """
            val expected = ActivityWithTimestamp(
                timestamp = DateTime(
                    year = 2019,
                    month = 1,
                    day = 1
                ),
                activities = emptyList()
            )
            assertThat(Json.parse(ActivityWithTimestamp.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = ActivityWithTimestamp(
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
            val expected = """
                {
                  "timestampMs": "1546300800000",
                  "activity": [
                    {
                      "type": "STILL",
                      "confidence": 100
                    }
                  ]
                }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(ActivityWithTimestamp.serializer(), sut)).isEqualTo(expected)
        }

        @Test
        fun `activity should not be contained in JSON when the property is empty`() {
            val sut = ActivityWithTimestamp(
                timestamp = DateTime(
                    year = 2019,
                    month = 1,
                    day = 1
                ),
                activities = emptyList()
            )
            val expected = """
                {
                  "timestampMs": "1546300800000"
                 }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(ActivityWithTimestamp.serializer(), sut)).isEqualTo(expected)
        }
    }
}