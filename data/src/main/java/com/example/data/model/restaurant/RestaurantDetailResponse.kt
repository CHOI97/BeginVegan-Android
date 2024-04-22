package com.example.data.model.restaurant

import com.example.beginvegan.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class RestaurantDetailResponse(
    @SerializedName("information") val information: com.example.data.model.restaurant.RestaurantDetail
): BaseResponse()

data class RestaurantDetail(
    @SerializedName("restaurant")
    val restaurant: com.example.data.model.restaurant.RestaurantDetailType,
    @SerializedName("menus")
    val menus: List<com.example.data.model.restaurant.Menus>
)
data class RestaurantDetailType(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("businessHours") val businessHours: String,
    @SerializedName("contactNumber") val contactNumber: String,
    @SerializedName("address") val address: com.example.data.model.restaurant.Address,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("kakaoMapUrl") val kakaoMapUrl: String,
    @SerializedName("imageUrl") val imageUrl:String,
    @SerializedName("imageSource") val imageSource: String?,
    @SerializedName("restaurantType") val restaurantType: String
)