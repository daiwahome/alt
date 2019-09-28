package com.github.daiwahome.alt.model

import com.soywiz.klock.DateTime
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
data class Location(
    @SerialName("timestampMs") val timestamp: DateTime,
    @SerialName("latitudeE7") val latitude: Double,
    @SerialName("longitudeE7") val longitude: Double,
    val accuracy: Int,
    val velocity: Int?,
    val heading: Int?,
    val altitude: Int?,
    val verticalAccuracy: Int?,
    @SerialName("activity") val activities: List<ActivityWithTimestamp>
) {

    @Serializer(forClass = Location::class)
    companion object : KSerializer<Location> {

        override val descriptor: SerialDescriptor
            get() = object : SerialClassDescImpl("Location") {
                init {
                    addElement("timestampMs")
                    addElement("latitudeE7")
                    addElement("longitudeE7")
                    addElement("accuracy")
                    addElement("velocity")
                    addElement("heading")
                    addElement("altitude")
                    addElement("verticalAccuracy")
                    addElement("activity")
                }
            }

        override fun deserialize(decoder: Decoder): Location {
            var timestampMs: String? = null
            var latitudeE7: Long? = null
            var longitudeE7: Long? = null
            var accuracy: Int? = null
            var velocity: Int? = null
            var heading: Int? = null
            var altitude: Int? = null
            var verticalAccuracy: Int? = null
            var activity: List<ActivityWithTimestamp> = emptyList()

            decoder.beginStructure(descriptor).apply {
                loop@while (true) {
                    when (val i = decodeElementIndex(descriptor)) {
                        CompositeDecoder.READ_DONE -> break@loop
                        0 -> timestampMs = decodeStringElement(descriptor, i)
                        1 -> latitudeE7 = decodeLongElement(descriptor, i)
                        2 -> longitudeE7 = decodeLongElement(descriptor, i)
                        3 -> accuracy = decodeIntElement(descriptor, i)
                        4 -> velocity = decodeIntElement(descriptor, i)
                        5 -> heading = decodeIntElement(descriptor, i)
                        6 -> altitude = decodeIntElement(descriptor, i)
                        7 -> verticalAccuracy = decodeIntElement(descriptor, i)
                        8 -> activity = decodeSerializableElement(descriptor, i, ActivityWithTimestamp.serializer().list)
                    }
                }
            }.endStructure(descriptor)

            return Location(
                timestamp = timestampMs?.let { DateTime.fromUnix(it.toLong()) }
                    ?: throw MissingFieldException("timestampMs"),
                latitude = latitudeE7?.let { it.toDouble() / 10e7 }
                    ?: throw MissingFieldException("latitudeE7"),
                longitude = longitudeE7?.let { it.toDouble() / 10e7 }
                    ?: throw MissingFieldException("longitudeE7"),
                accuracy = accuracy ?: throw MissingFieldException("accuracy"),
                velocity = velocity,
                heading = heading,
                altitude = altitude,
                verticalAccuracy = verticalAccuracy,
                activities = activity
            )
        }

        override fun serialize(encoder: Encoder, obj: Location) {
            encoder.beginStructure(descriptor).apply {
                encodeStringElement(descriptor, 0, obj.timestamp.unixMillisLong.toString())
                encodeLongElement(descriptor, 1, (obj.latitude * 10e7).toLong())
                encodeLongElement(descriptor, 2, (obj.longitude * 10e7).toLong())
                encodeIntElement(descriptor, 3, obj.accuracy)
                obj.velocity?.let { encodeIntElement(descriptor, 4, it) }
                obj.heading?.let { encodeIntElement(descriptor, 5, it) }
                obj.altitude?.let { encodeIntElement(descriptor, 6, it) }
                obj.verticalAccuracy?.let { encodeIntElement(descriptor, 7, it) }
                if (obj.activities.isNotEmpty()) {
                    encodeSerializableElement(descriptor, 8, ActivityWithTimestamp.serializer().list, obj.activities)
                }
            }.endStructure(descriptor)
        }
    }
}