package com.github.daiwahome.alt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressComponent(
    @SerialName("long_name") val longName: String,
    @SerialName("short_name") val shortName: String,
    val types: List<AddressType>
)