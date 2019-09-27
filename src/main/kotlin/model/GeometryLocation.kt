package com.github.daiwahome.alt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeometryLocation(
    @SerialName("lat") val latitude: Double,
    @SerialName("lng") val longitude: Double
)