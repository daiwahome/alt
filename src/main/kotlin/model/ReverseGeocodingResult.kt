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
import kotlinx.serialization.list

@Serializable
data class ReverseGeocodingResult(
    @SerialName("address_components") val addressComponents: List<AddressComponent>,
    @SerialName("formatted_address") val formattedAddress: String,
    val geometry: Geometry,
    @SerialName("place_id") val placeId: String,
    @SerialName("plus_code") val plusCode: PlusCode?,
    val types: List<AddressType>
) {

    @Serializer(forClass = ReverseGeocodingResult::class)
    companion object : KSerializer<ReverseGeocodingResult> {

        override val descriptor: SerialDescriptor
            get() = object : SerialClassDescImpl("ReverseGeocodingResult") {
                init {
                    addElement("address_components")
                    addElement("formatted_address")
                    addElement("geometry")
                    addElement("place_id")
                    addElement("plus_code")
                    addElement("types")
                }
            }

        override fun deserialize(decoder: Decoder): ReverseGeocodingResult {
            var addressComponents: List<AddressComponent> = emptyList()
            var formattedAddress: String? = null
            var geometry: Geometry? = null
            var placeId: String? = null
            var plusCode: PlusCode? = null
            var types: List<AddressType> = emptyList()

            decoder.beginStructure(descriptor).apply {
                loop@while (true) {
                    when (val i = decodeElementIndex(descriptor)) {
                        CompositeDecoder.READ_DONE -> break@loop
                        0 -> addressComponents = decodeSerializableElement(descriptor, i, AddressComponent.serializer().list)
                        1 -> formattedAddress = decodeStringElement(descriptor, i)
                        2 -> geometry = decodeSerializableElement(descriptor, i, Geometry.serializer())
                        3 -> placeId = decodeStringElement(descriptor, i)
                        4 -> plusCode = decodeSerializableElement(descriptor, i, PlusCode.serializer())
                        5 -> types = decodeSerializableElement(descriptor, i, AddressTypeSerializer.list)
                    }
                }
            }.endStructure(descriptor)

            return ReverseGeocodingResult(
                addressComponents = addressComponents,
                formattedAddress = formattedAddress ?: throw MissingFieldException("formatted_address"),
                geometry = geometry ?: throw MissingFieldException("geometry"),
                placeId = placeId ?: throw MissingFieldException("place_id"),
                plusCode = plusCode,
                types = types
            )
        }

        override fun serialize(encoder: Encoder, obj: ReverseGeocodingResult) {
            encoder.beginStructure(descriptor).apply {
                if (obj.addressComponents.isNotEmpty()) {
                    encodeSerializableElement(descriptor, 0, AddressComponent.serializer().list, obj.addressComponents)
                }
                encodeStringElement(descriptor, 1, obj.formattedAddress)
                encodeSerializableElement(descriptor, 2, Geometry.serializer(), obj.geometry)
                encodeStringElement(descriptor, 3, obj.placeId)
                obj.plusCode?.let { encodeSerializableElement(descriptor, 4, PlusCode.serializer(), it) }
                if (obj.types.isNotEmpty()) {
                    encodeSerializableElement(descriptor, 5, AddressTypeSerializer.list, obj.types)
                }
            }.endStructure(descriptor)
        }
    }
}
