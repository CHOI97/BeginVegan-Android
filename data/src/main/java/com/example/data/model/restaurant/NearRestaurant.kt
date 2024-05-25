//package com.example.data.model.restaurant
//
//import com.google.gson.annotations.SerializedName
//import java.io.Serializable
//
//data class NearRestaurant(
//    @SerializedName("id") val id: Int,
//    @SerializedName("name") val name: String,
//    @SerializedName("businessHours") val businessHours: String,
//    @SerializedName("address") val address: com.example.data.model.restaurant.Address,
//    @SerializedName("latitude") val latitude: String,
//    @SerializedName("longitude") val longitude: String,
//    @SerializedName("imageUrl") val imageUrl: String,
//    @SerializedName("menus") val menus: List<com.example.data.model.restaurant.NearRestaurantMenus>
//): Serializable
//data class NearRestaurantMenus(
//    @SerializedName("id") val id: Int,
//    @SerializedName("imageUrl") val imageUrl: String?
//)
