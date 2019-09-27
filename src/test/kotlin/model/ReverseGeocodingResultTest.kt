package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.AddressComponent
import com.github.daiwahome.alt.model.AddressType
import com.github.daiwahome.alt.model.Geometry
import com.github.daiwahome.alt.model.GeometryLocation
import com.github.daiwahome.alt.model.GeometryLocationType
import com.github.daiwahome.alt.model.GeometryBoundary
import com.github.daiwahome.alt.model.PlaceType
import com.github.daiwahome.alt.model.PlusCode
import com.github.daiwahome.alt.model.ReverseGeocodingResult
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class ReverseGeocodingResultTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            val sut = """
                {
                  "address_components": [
                    {
                      "long_name" : "New York",
                      "short_name" : "NY",
                      "types" : [
                        "administrative_area_level_1",
                        "political"
                      ]
                    },
                    {
                      "long_name": "United States",
                      "short_name": "US",
                      "types": [
                        "country",
                        "political"
                      ]
                    }
                  ],
                  "formatted_address" : "279 Bedford Ave, Brooklyn, NY 11211, USA",
                  "geometry" : {
                    "bounds" : {
                      "northeast" : {
                        "lat" : 40.71559738029149,
                        "lng" : -73.9600613197085
                      },
                      "southwest" : {
                        "lat" : 40.71289941970849,
                        "lng" : -73.96275928029151
                      }
                    },
                    "location" : {
                      "lat" : 40.7142484,
                      "lng" : -73.9614103
                    },
                    "location_type" : "ROOFTOP",
                    "viewport" : {
                      "northeast" : {
                        "lat" : 40.71559738029149,
                        "lng" : -73.9600613197085
                      },
                      "southwest" : {
                        "lat" : 40.71289941970849,
                        "lng" : -73.96275928029151
                      }
                    }
                  },
                  "place_id" : "ChIJT2x8Q2BZwokRpBu2jUzX3dE",
                  "types" : [
                    "bakery",
                    "cafe",
                    "establishment",
                    "food",
                    "point_of_interest",
                    "store"
                  ]
                }
            """
            val expected = ReverseGeocodingResult(
                addressComponents = listOf(
                    AddressComponent(
                        longName = "New York",
                        shortName = "NY",
                        types = listOf(
                            AddressType.ADMINISTRATIVE_AREA_LEVEL_1,
                            AddressType.POLITICAL
                        )
                    ),
                    AddressComponent(
                        longName = "United States",
                        shortName = "US",
                        types = listOf(
                            AddressType.COUNTRY,
                            AddressType.POLITICAL
                        )
                    )
                ),
                formattedAddress = "279 Bedford Ave, Brooklyn, NY 11211, USA",
                geometry = Geometry(
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
                ),
                placeId = "ChIJT2x8Q2BZwokRpBu2jUzX3dE",
                types = listOf(
                    PlaceType.BAKERY,
                    PlaceType.CAFE,
                    PlaceType.ESTABLISHMENT,
                    PlaceType.FOOD,
                    PlaceType.POINT_OF_INTEREST,
                    PlaceType.STORE
                )
            )
            assertThat(Json.parse(ReverseGeocodingResult.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = ReverseGeocodingResult(
                addressComponents = listOf(
                    AddressComponent(
                        longName = "New York",
                        shortName = "NY",
                        types = listOf(
                            AddressType.ADMINISTRATIVE_AREA_LEVEL_1,
                            AddressType.POLITICAL
                        )
                    ),
                    AddressComponent(
                        longName = "United States",
                        shortName = "US",
                        types = listOf(
                            AddressType.COUNTRY,
                            AddressType.POLITICAL
                        )
                    )
                ),
                formattedAddress = "279 Bedford Ave, Brooklyn, NY 11211, USA",
                geometry = Geometry(
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
                ),
                placeId = "ChIJT2x8Q2BZwokRpBu2jUzX3dE",
                types = listOf(
                    PlaceType.BAKERY,
                    PlaceType.CAFE,
                    PlaceType.ESTABLISHMENT,
                    PlaceType.FOOD,
                    PlaceType.POINT_OF_INTEREST,
                    PlaceType.STORE
                )
            )
            val expected = """
                {
                  "address_components": [
                    {
                      "long_name" : "New__York",
                      "short_name" : "NY",
                      "types" : [
                        "administrative_area_level_1",
                        "political"
                      ]
                    },
                    {
                      "long_name": "United__States",
                      "short_name": "US",
                      "types": [
                        "country",
                        "political"
                      ]
                    }
                  ],
                  "formatted_address" : "279__Bedford__Ave,__Brooklyn,__NY__11211,__USA",
                  "geometry" : {
                    "bounds" : {
                      "northeast" : {
                        "lat" : 40.71559738029149,
                        "lng" : -73.9600613197085
                      },
                      "southwest" : {
                        "lat" : 40.71289941970849,
                        "lng" : -73.96275928029151
                      }
                    },
                    "location" : {
                      "lat" : 40.7142484,
                      "lng" : -73.9614103
                    },
                    "location_type" : "ROOFTOP",
                    "viewport" : {
                      "northeast" : {
                        "lat" : 40.71559738029149,
                        "lng" : -73.9600613197085
                      },
                      "southwest" : {
                        "lat" : 40.71289941970849,
                        "lng" : -73.96275928029151
                      }
                    }
                  },
                  "place_id" : "ChIJT2x8Q2BZwokRpBu2jUzX3dE",
                  "types" : [
                    "bakery",
                    "cafe",
                    "establishment",
                    "food",
                    "point_of_interest",
                    "store"
                  ]
                }
            """.replace("\\s".toRegex(), "")
                .replace("__".toRegex(), " ")
            assertThat(Json.stringify(ReverseGeocodingResult.serializer(), sut)).isEqualTo(expected)
        }
    }
}