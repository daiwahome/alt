package com.github.daiwahome.alt.model

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor

@Serializable(with = GeometryLocationTypeSerializer::class)
enum class GeometryLocationType {
    ROOFTOP,
    RANGE_INTERPOLATED,
    GEOMETRIC_CENTER,
    APPROXIMATE,
}

@Serializer(forClass = GeometryLocationType::class)
object GeometryLocationTypeSerializer : KSerializer<GeometryLocationType> {

    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder): GeometryLocationType =
        GeometryLocationType.valueOf(decoder.decodeString())

    override fun serialize(encoder: Encoder, obj: GeometryLocationType) =
        encoder.encodeString(obj.toString())
}
