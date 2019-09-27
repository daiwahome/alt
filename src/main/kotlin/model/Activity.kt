package com.github.daiwahome.alt.model

import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val type: ActivityType,
    val confidence: Int
)