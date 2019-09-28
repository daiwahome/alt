package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.Geometry
import com.github.daiwahome.alt.model.GeometryLocation
import com.github.daiwahome.alt.model.GeometryLocationType
import com.github.daiwahome.alt.model.GeometryBoundary
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class GeometryTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            //language=JSON
            val sut = """
                {
                  "bounds": {
                    "northeast": {
                      "lat": 40.71559738029149,
                      "lng": -73.9600613197085
                    },
                    "southwest": {
                      "lat": 40.71289941970849,
                      "lng": -73.96275928029151
                    }
                  },
                  "location": {
                    "lat": 40.7142484,
                    "lng": -73.9614103
                  },
                  "location_type": "ROOFTOP",
                  "viewport": {
                    "northeast": {
                      "lat": 40.71559738029149,
                      "lng": -73.9600613197085
                    },
                    "southwest": {
                      "lat": 40.71289941970849,
                      "lng": -73.96275928029151
                    }
                  }
                }
            """
            val expected = Geometry(
                boundary = GeometryBoundary(
                    northeast = GeometryLocation(
                        latitude = 40.71559738029149,
                        longitude = -73.9600613197085
                    ),
                    southwest = GeometryLocation(
                        latitude = 40.71289941970849,
                        longitude = -73.96275928029151
                    )
                ),
                location = GeometryLocation(
                    latitude = 40.7142484,
                    longitude = -73.9614103
                ),
                locationType = GeometryLocationType.ROOFTOP,
                viewport = GeometryBoundary(
                    northeast = GeometryLocation(
                        latitude = 40.71559738029149,
                        longitude = -73.9600613197085
                    ),
                    southwest = GeometryLocation(
                        latitude = 40.71289941970849,
                        longitude = -73.96275928029151
                    )
                )
            )
            assertThat(Json.parse(Geometry.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = Geometry(
                boundary = GeometryBoundary(
                    northeast = GeometryLocation(
                        latitude = 40.71559738029149,
                        longitude = -73.9600613197085
                    ),
                    southwest = GeometryLocation(
                        latitude = 40.71289941970849,
                        longitude = -73.96275928029151
                    )
                ),
                location = GeometryLocation(
                    latitude = 40.7142484,
                    longitude = -73.9614103
                ),
                locationType = GeometryLocationType.ROOFTOP,
                viewport = GeometryBoundary(
                    northeast = GeometryLocation(
                        latitude = 40.71559738029149,
                        longitude = -73.9600613197085
                    ),
                    southwest = GeometryLocation(
                        latitude = 40.71289941970849,
                        longitude = -73.96275928029151
                    )
                )
            )
            //language=JSON
            val expected = """
                {
                  "bounds": {
                    "northeast": {
                      "lat": 40.71559738029149,
                      "lng": -73.9600613197085
                    },
                    "southwest": {
                      "lat": 40.71289941970849,
                      "lng": -73.96275928029151
                    }
                  },
                  "location": {
                    "lat": 40.7142484,
                    "lng": -73.9614103
                  },
                  "location_type": "ROOFTOP",
                  "viewport": {
                    "northeast": {
                      "lat": 40.71559738029149,
                      "lng": -73.9600613197085
                    },
                    "southwest": {
                      "lat": 40.71289941970849,
                      "lng": -73.96275928029151
                    }
                  }
                }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(Geometry.serializer(), sut)).isEqualTo(expected)
        }
    }
}