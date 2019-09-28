package service

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.AddressComponent
import com.github.daiwahome.alt.model.AddressType
import com.github.daiwahome.alt.model.Geometry
import com.github.daiwahome.alt.model.GeometryBoundary
import com.github.daiwahome.alt.model.GeometryLocation
import com.github.daiwahome.alt.model.GeometryLocationType
import com.github.daiwahome.alt.model.PlusCode
import com.github.daiwahome.alt.model.ReverseGeocodingResponse
import com.github.daiwahome.alt.model.ReverseGeocodingResult
import com.github.daiwahome.alt.service.GoogleApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.ContentType
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class GoogleApiServiceTest {

    private val response = ReverseGeocodingResponse(
        plusCode = PlusCode(
            compoundCode = "P27Q+MC New York, NY, USA",
            globalCode = "87G8P27Q+MC"
        ),
        results = listOf(
            ReverseGeocodingResult(
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
                    AddressType.BAKERY,
                    AddressType.CAFE,
                    AddressType.ESTABLISHMENT,
                    AddressType.FOOD,
                    AddressType.POINT_OF_INTEREST,
                    AddressType.STORE
                )
            )
        ),
        status = "OK"
    )

    private val client: HttpClient = HttpClient(MockEngine) {
        val content: String = Json.stringify(ReverseGeocodingResponse.serializer(), response)
        engine {
            addHandler {
                when (it.url.fullPath) {
                    "/maps/api/geocode/json?latlng=0.0%2C0.0&key=foo" -> respond(
                        content = content,
                        headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
                    )
                    else -> error("Unhandled ${it.url.fullPath}")
                }
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    private val sut = GoogleApiService(
        client
    )

    @Test
    fun requestReverseGeocoding() {
        // setup
        val latitude = 0.0
        val longitude = 0.0
        val apiKey = "foo"

        // exercise
        val actual = sut.requestReverseGeocoding(latitude, longitude, apiKey)

        // verify
        assertThat(actual).isEqualTo(response)
    }
}
