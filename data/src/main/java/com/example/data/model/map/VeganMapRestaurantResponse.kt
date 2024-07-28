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
    val restaurantId: Long,
    @Json(name = "restaurantName")
    val restaurantName: String,
    @Json(name = "restaurantType")
    val restaurantType: String?,
    @Json(name = "distance")
    val distance: Long,
    @Json(name = "rate")
    val rate: Float,
    @Json(name = "thumbnail")
    val thumbnail: String,
    @Json(name = "latitude")
    val latitude: String,
    @Json(name = "longitude")
    val longitude: String
)