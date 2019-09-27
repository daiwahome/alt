package com.github.daiwahome.alt.model

import kotlinx.serialization.CompositeDecoder
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.MissingFieldException
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.SerialClassDescImpl

@Serializable
data class PlusCode(
    @SerialName("compound_code") val compoundCode: String? = null,
    @SerialName("global_code") val globalCode: String
) {

    @Serializer(forClass = PlusCode::class)
    companion object : KSerializer<PlusCode> {

        override val descriptor: SerialDescriptor
            get() = object : SerialClassDescImpl("PlusCode") {
                init {
                    addElement("compound_code")
                    addElement("global_code")
                }
            }

        override fun deserialize(decoder: Decoder): PlusCode {
            var compoundCode: String? = null
            var globalCode: String? = null

            decoder.beginStructure(descriptor).apply {
                loop@while (true) {
                    when (val i = decodeElementIndex(descriptor)) {
                        CompositeDecoder.READ_DONE -> break@loop
                        0 -> compoundCode = decodeStringElement(descriptor, i)
                        1 -> globalCode = decodeStringElement(descriptor, i)
                    }
                }
            }.endStructure(descriptor)

            return PlusCode(
                compoundCode = compoundCode,
                globalCode = globalCode ?: throw MissingFieldException("globalCode")
            )
        }

        override fun serialize(encoder: Encoder, obj: PlusCode) {
            encoder.beginStructure(descriptor).apply {
                if (obj.compoundCode != null) {
                    encodeStringElement(descriptor, 0, obj.compoundCode)
                }
                encodeStringElement(descriptor, 1, obj.globalCode)
            }.endStructure(descriptor)
        }
    }
}
