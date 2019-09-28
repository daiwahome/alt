package com.github.daiwahome.alt.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationHistory(
    val locations: List<Location>
)