package com.github.daiwahome.alt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReverseGeocodingResult(
    @SerialName("address_components") val addressComponents: List<AddressComponent>,
    @SerialName("formatted_address") val formattedAddress: String,
    val geometry: Geometry,
    @SerialName("place_id") val placeId: String,
    val types: List<PlaceType>
)