package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.GeometryLocation
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class GeometryLocationTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            val sut = """
                {
                  "lat" : 40.7142484,
                  "lng" : -73.9614103
                }
            """
            val expected = GeometryLocation(
                latitude = 40.7142484,
                longitude = -73.9614103
            )
            assertThat(Json.parse(GeometryLocation.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = GeometryLocation(
                latitude = 40.7142484,
                longitude = -73.9614103
            )
            val expected = """
                {
                  "lat" : 40.7142484,
                  "lng" : -73.9614103
                }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(GeometryLocation.serializer(), sut)).isEqualTo(expected)
        }
    }
}