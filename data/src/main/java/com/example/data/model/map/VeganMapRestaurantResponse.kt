package com.example.data.model.map

import com.squareup.moshi.Json

data class VeganMapRestaurantResponse(
    @Json(name = "check")
    val check: Boolean,
    @Json(name = "information")
    val information: List<VeganMapRestaurantDto>
)

data class VeganMapRestaurantDto(
    @Json(name = "restaurantId")
    val restaurantId: Int,
    @Json(name = "restaurantName")
    val restaurantName: String,
    @Json(name = "restaurantType")
    val restaurantType: RestaurantType?,
    @Json(name = "distance")
    val distance: Double,
    @Json(name = "rate")
    val rate: Double,
    @Json(name = "thumbnail")
    val thumbnail: String,
    @Json(name = "latitude")
    val latitude: String,
    @Json(name = "longitude")
    val longitude: String
)