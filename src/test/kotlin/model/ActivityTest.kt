package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.Activity
import com.github.daiwahome.alt.model.ActivityType
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class ActivityTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            val sut = """
                {
                  "type": "STILL",
                  "confidence": 100
                }
            """
            val expected = Activity(
                type = ActivityType.STILL,
                confidence = 100
            )
            assertThat(Json.parse(Activity.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = Activity(
                type = ActivityType.STILL,
                confidence = 100
            )
            val expected = """
                {
                  "type": "STILL",
                  "confidence": 100
                }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(Activity.serializer(), sut)).isEqualTo(expected)
        }
    }
}