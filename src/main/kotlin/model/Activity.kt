package com.github.daiwahome.alt.model

import com.soywiz.klock.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val type: ActivityType,
    val confidence: Int
)