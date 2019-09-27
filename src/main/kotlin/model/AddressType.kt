package com.github.daiwahome.alt.model

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor

@Serializable(with = AddressTypeSerializer::class)
enum class AddressType {
    STREET_ADDRESS,
    ROUTE,
    INTERSECTION,
    POLITICAL,
    COUNTRY,
    ADMINISTRATIVE_AREA_LEVEL_1,
    ADMINISTRATIVE_AREA_LEVEL_2,
    ADMINISTRATIVE_AREA_LEVEL_3,
    ADMINISTRATIVE_AREA_LEVEL_4,
    ADMINISTRATIVE_AREA_LEVEL_5,
    COLLOQUIAL_AREA,
    LOCALITY,
    SUBLOCALITY,
    SUBLOCALITY_LEVEL_1,
    SUBLOCALITY_LEVEL_2,
    SUBLOCALITY_LEVEL_3,
    SUBLOCALITY_LEVEL_4,
    SUBLOCALITY_LEVEL_5,
    NEIGHBORHOOD,
    PREMISE,
    SUBPREMISE,
    POSTAL_CODE,
    NATURAL_FEATURE,
    AIRPORT,
    PARK,
    POINT_OF_INTEREST,
    ESTABLISHMENT,
}

@Serializer(forClass = AddressType::class)
object AddressTypeSerializer : KSerializer<AddressType> {

    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder): AddressType =
        AddressType.valueOf(decoder.decodeString().toUpperCase())

    override fun serialize(encoder: Encoder, obj: AddressType) =
        encoder.encodeString(obj.toString().toLowerCase())
}
