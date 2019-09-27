package com.github.daiwahome.alt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReverseGeocodingResponse(
    @SerialName("plus_code") val plusCode: PlusCode,
    val results: List<ReverseGeocodingResult>,
    val status: String
)