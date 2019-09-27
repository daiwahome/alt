package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.GeometryLocation
import com.github.daiwahome.alt.model.GeometryBoundary
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class GeometryBoundaryTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            val sut = """
                {
                  "northeast" : {
                    "lat" : 40.71559738029149,
                    "lng" : -73.9600613197085
                  },
                  "southwest" : {
                    "lat" : 40.71289941970849,
                    "lng" : -73.96275928029151
                  }
                }
            """
            val expected = GeometryBoundary(
                northeast = GeometryLocation(
                    latitude = 40.71559738029149,
                    longitude = -73.9600613197085
                ),
                southwest = GeometryLocation(
                    latitude = 40.71289941970849,
                    longitude = -73.96275928029151
                )
            )
            assertThat(Json.parse(GeometryBoundary.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = GeometryBoundary(
                northeast = GeometryLocation(
                    latitude = 40.71559738029149,
                    longitude = -73.9600613197085
                ),
                southwest = GeometryLocation(
                    latitude = 40.71289941970849,
                    longitude = -73.96275928029151
                )
            )
            val expected = """
                {
                  "northeast" : {
                    "lat" : 40.71559738029149,
                    "lng" : -73.9600613197085
                  },
                  "southwest" : {
                    "lat" : 40.71289941970849,
                    "lng" : -73.96275928029151
                  }
                }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(GeometryBoundary.serializer(), sut)).isEqualTo(expected)
        }
    }
}