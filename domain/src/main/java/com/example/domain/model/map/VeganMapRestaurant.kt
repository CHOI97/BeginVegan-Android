package com.example.domain.model.map

data class VeganMapRestaurant(
    val id: Long,
    val name: String,
    val type: String?,
    val distance: Long,
    val rate: Float,
    val latitude: String,
    val longitude: String,
    val thumbnail: String
)