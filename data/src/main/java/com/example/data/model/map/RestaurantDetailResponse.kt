package com.example.data.model.map

import com.squareup.moshi.Json

data class RestaurantDetailResponse (
    @Json(name = "check")
    val check: Boolean,
    @Json(name = "information")
    val information: List<RestaurantDto>
)
data class AddressDto(
    @Json(name = "province")
    val province: String,
    @Json(name = "city")
    val city: String,
    @Json(name = "roadName")
    val roadName: String,
    @Json(name = "detailAddress")
    val detailAddress: String
)

data class MenuDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)

data class RestaurantDto(
    @Json(name = "restaurantId")
    val restaurantId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "restaurantType")
    val restaurantType: String,
    @Json(name = "address")
    val address: AddressDto,
    @Json(name = "distance")
    val distance: Double,
    @Json(name = "rate")
    val rate: Double,
    @Json(name = "reviewCount")
    val reviewCount: Int,
    @Json(name = "contactNumber")
    val contactNumber: String,
    @Json(name = "bookmark")
    val bookmark: Boolean,
    @Json(name = "menus")
    val menus: List<MenuDto>
)