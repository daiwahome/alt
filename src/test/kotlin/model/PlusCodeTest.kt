package model

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.daiwahome.alt.model.PlusCode
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@kotlinx.serialization.UnstableDefault
internal class PlusCodeTest {

    @Nested
    inner class Deserialize {

        @Test
        fun deserialize() {
            //language=JSON
            val sut = """
                {
                  "compound_code": "P27Q+MC New York, NY, USA",
                  "global_code": "87G8P27Q+MC"
                }
            """
            val expected = PlusCode(
                compoundCode = "P27Q+MC New York, NY, USA",
                globalCode = "87G8P27Q+MC"
            )
            assertThat(Json.parse(PlusCode.serializer(), sut)).isEqualTo(expected)
        }

        @Test
        fun `compoundCode should be null when the attribute is missing`() {
            //language=JSON
            val sut = """
                {
                  "global_code": "87G8P27Q+MC"
                }
            """
            val expected = PlusCode(
                compoundCode = null,
                globalCode = "87G8P27Q+MC"
            )
            assertThat(Json.parse(PlusCode.serializer(), sut)).isEqualTo(expected)
        }
    }

    @Nested
    inner class Serialize {

        @Test
        fun serialize() {
            val sut = PlusCode(
                compoundCode = "P27Q+MC New York, NY, USA",
                globalCode = "87G8P27Q+MC"
            )
            //language=JSON
            val expected = """
                {
                  "compound_code": "P27Q+MC__New__York,__NY,__USA",
                  "global_code": "87G8P27Q+MC"
                }
            """.replace("\\s".toRegex(), "")
                .replace("__".toRegex(), " ")
            assertThat(Json.stringify(PlusCode.serializer(), sut)).isEqualTo(expected)
        }

        @Test
        fun `compoundCode should not be contained when the property is null`() {
            val sut = PlusCode(
                compoundCode = null,
                globalCode = "87G8P27Q+MC"
            )
            //language=JSON
            val expected = """
                {
                  "global_code": "87G8P27Q+MC"
                }
            """.replace("\\s".toRegex(), "")
            assertThat(Json.stringify(PlusCode.serializer(), sut)).isEqualTo(expected)
        }
    }
}