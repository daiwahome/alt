package com.github.daiwahome.alt.model

import kotlinx.serialization.Serializable

@Serializable
data class GeometryBoundary(
    val northeast: GeometryLocation,
    val southwest: GeometryLocation
)