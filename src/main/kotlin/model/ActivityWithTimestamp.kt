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
data class ActivityWithTimestamp(
    @SerialName("timestampMs") val timestamp: DateTime,
    @SerialName("activity") val activities: List<Activity>
) {

    @Serializer(forClass = ActivityWithTimestamp::class)
    companion object : KSerializer<ActivityWithTimestamp> {

        override val descriptor: SerialDescriptor
            get() = object : SerialClassDescImpl("ActivityWithTimestamp") {
                init {
                    addElement("timestampMs")
                    addElement("activity")
                }
            }

        override fun deserialize(decoder: Decoder): ActivityWithTimestamp {
            var timestampMs: String? = null
            var activity: List<Activity> = emptyList()

            decoder.beginStructure(descriptor).apply {
                loop@while (true) {
                    when (val i = decodeElementIndex(descriptor)) {
                        CompositeDecoder.READ_DONE -> break@loop
                        0 -> timestampMs = decodeStringElement(descriptor, i)
                        1 -> activity = decodeSerializableElement(descriptor, i, Activity.serializer().list)
                    }
                }
            }.endStructure(descriptor)

            return ActivityWithTimestamp(
                timestamp = timestampMs?.let { DateTime.fromUnix(it.toLong()) }
                    ?: throw MissingFieldException("timestampMs"),
                activities = activity
            )
        }

        override fun serialize(encoder: Encoder, obj: ActivityWithTimestamp) {
            encoder.beginStructure(descriptor).apply {
                encodeStringElement(descriptor, 0, obj.timestamp.unixMillisLong.toString())
                if (obj.activities.isNotEmpty()) {
                    encodeSerializableElement(descriptor, 1, Activity.serializer().list, obj.activities)
                }
            }.endStructure(descriptor)
        }
    }
}
