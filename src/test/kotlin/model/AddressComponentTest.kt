package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.AddressComponent
import com.github.daiwahome.alt.model.AddressType
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class AddressComponentTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            val sut = """
                {
                  "long_name" : "New York",
                  "short_name" : "NY",
                  "types" : [
                    "administrative_area_level_1",
                    "political"
                  ]
                }
            """
            val expected = AddressComponent(
                longName = "New York",
                shortName = "NY",
                types = listOf(
                    AddressType.ADMINISTRATIVE_AREA_LEVEL_1,
                    AddressType.POLITICAL
                )
            )
            assertThat(Json.parse(AddressComponent.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = AddressComponent(
                longName = "New York",
                shortName = "NY",
                types = listOf(
                    AddressType.ADMINISTRATIVE_AREA_LEVEL_1,
                    AddressType.POLITICAL
                )
            )
            val expected = """
                {
                  "long_name" : "New__York",
                  "short_name" : "NY",
                  "types" : [
                    "administrative_area_level_1",
                    "political"
                  ]
                }
            """.replace("\\s".toRegex(), "")
                .replace("__".toRegex(), " ")
            assertThat(Json.stringify(AddressComponent.serializer(), sut)).isEqualTo(expected)
        }
    }
}