package com.example.domain.repository.map

interface VeganMapRepository {
    suspend fun getNearRestaurantWithPermission()

    suspend fun getNearRestaurantWithOutPermission()

    suspend fun getNearRestaurantMap()

    suspend fun getRestaurantDetail()
}