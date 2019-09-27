package com.github.daiwahome.alt.model

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor

@Serializable(with = PlaceTypeSerializer::class)
enum class PlaceType {
    ACCOUNTING,
    AIRPORT,
    AMUSEMENT_PARK,
    AQUARIUM,
    ART_GALLERY,
    ATM,
    BAKERY,
    BANK,
    BAR,
    BEAUTY_SALON,
    BICYCLE_STORE,
    BOOK_STORE,
    BOWLING_ALLEY,
    BUS_STATION,
    CAFE,
    CAMPGROUND,
    CAR_DEALER,
    CAR_RENTAL,
    CAR_REPAIR,
    CAR_WASH,
    CASINO,
    CEMETERY,
    CHURCH,
    CITY_HALL,
    CLOTHING_STORE,
    CONVENIENCE_STORE,
    COURTHOUSE,
    DENTIST,
    DEPARTMENT_STORE,
    DOCTOR,
    DRUGSTORE,
    ELECTRICIAN,
    ELECTRONICS_STORE,
    EMBASSY,
    ESTABLISHMENT,
    FIRE_STATION,
    FLORIST,
    FOOD,
    FUNERAL_HOME,
    FURNITURE_STORE,
    GAS_STATION,
    GROCERY_OR_SUPERMARKET,
    GYM,
    HAIR_CARE,
    HARDWARE_STORE,
    HINDU_TEMPLE,
    HOME_GOODS_STORE,
    HOSPITAL,
    INSURANCE_AGENCY,
    JEWELRY_STORE,
    LAUNDRY,
    LAWYER,
    LIBRARY,
    LIGHT_RAIL_STATION,
    LIQUOR_STORE,
    LOCAL_GOVERNMENT_OFFICE,
    LOCKSMITH,
    LODGING,
    MEAL_DELIVERY,
    MEAL_TAKEAWAY,
    MOSQUE,
    MOVIE_RENTAL,
    MOVIE_THEATER,
    MOVING_COMPANY,
    MUSEUM,
    NATURAL_FEATURE,
    NIGHT_CLUB,
    PAINTER,
    PARK,
    PARKING,
    PET_STORE,
    PHARMACY,
    PHYSIOTHERAPIST,
    PLUMBER,
    POINT_OF_INTEREST,
    POLICE,
    POST_OFFICE,
    PRIMARY_SCHOOL,
    REAL_ESTATE_AGENCY,
    RESTAURANT,
    ROOFING_CONTRACTOR,
    RV_PARK,
    SCHOOL,
    SECONDARY_SCHOOL,
    SHOE_STORE,
    SHOPPING_MALL,
    SPA,
    STADIUM,
    STORAGE,
    STORE,
    SUBWAY_STATION,
    SUPERMARKET,
    SYNAGOGUE,
    TAXI_STAND,
    TOURIST_ATTRACTION,
    TRAIN_STATION,
    TRANSIT_STATION,
    TRAVEL_AGENCY,
    UNIVERSITY,
    VETERINARY_CARE,
    ZOO,
}

@Serializer(forClass = PlaceType::class)
object PlaceTypeSerializer : KSerializer<PlaceType> {

    override val descriptor: SerialDescriptor
        get() = StringDescriptor

    override fun deserialize(decoder: Decoder): PlaceType =
        PlaceType.valueOf(decoder.decodeString().toUpperCase())

    override fun serialize(encoder: Encoder, obj: PlaceType) =
        encoder.encodeString(obj.toString().toLowerCase())
}
