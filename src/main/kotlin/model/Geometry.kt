package com.github.daiwahome.alt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geometry(
    @SerialName("bounds") val boundary: GeometryBoundary,
    val location: GeometryLocation,
    @SerialName("location_type") val locationType: GeometryLocationType,
    val viewport: GeometryBoundary
)