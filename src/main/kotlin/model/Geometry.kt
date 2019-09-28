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
data class Geometry(
    @SerialName("bounds") val boundary: GeometryBoundary?,
    val location: GeometryLocation,
    @SerialName("location_type") val locationType: GeometryLocationType,
    val viewport: GeometryBoundary
) {

    @Serializer(forClass = Geometry::class)
    companion object : KSerializer<Geometry> {

        override val descriptor: SerialDescriptor
            get() = object : SerialClassDescImpl("Geometry") {
                init {
                    addElement("bounds")
                    addElement("location")
                    addElement("location_type")
                    addElement("viewport")
                }
            }

        override fun deserialize(decoder: Decoder): Geometry {
            var bounds: GeometryBoundary? = null
            var location: GeometryLocation? = null
            var locationType: GeometryLocationType? = null
            var viewport: GeometryBoundary? = null

            decoder.beginStructure(descriptor).apply {
                loop@while (true) {
                    when (val i = decodeElementIndex(descriptor)) {
                        CompositeDecoder.READ_DONE -> break@loop
                        0 -> bounds = decodeSerializableElement(descriptor, i, GeometryBoundary.serializer())
                        1 -> location = decodeSerializableElement(descriptor, i, GeometryLocation.serializer())
                        2 -> locationType = decodeSerializableElement(descriptor, i, GeometryLocationTypeSerializer)
                        3 -> viewport = decodeSerializableElement(descriptor, i, GeometryBoundary.serializer())
                    }
                }
            }.endStructure(descriptor)

            return Geometry(
                boundary = bounds,
                location = location ?: throw MissingFieldException("location"),
                locationType = locationType ?: throw MissingFieldException("locationType"),
                viewport = viewport ?: throw MissingFieldException("viewport")
            )
        }

        override fun serialize(encoder: Encoder, obj: Geometry) {
            encoder.beginStructure(descriptor).apply {
                obj.boundary?.let { encodeSerializableElement(descriptor, 0, GeometryBoundary.serializer(), it) }
                encodeSerializableElement(descriptor, 1, GeometryLocation.serializer(), obj.location)
                encodeSerializableElement(descriptor, 2, GeometryLocationTypeSerializer, obj.locationType)
                encodeSerializableElement(descriptor, 3, GeometryBoundary.serializer(), obj.viewport)
            }.endStructure(descriptor)
        }
    }
}
