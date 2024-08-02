package com.example.domain.repository.map

import com.example.domain.model.map.RestaurantDetail
import com.example.domain.model.map.VeganMapRestaurant
import kotlinx.coroutines.flow.Flow

interface VeganMapRepository {
    suspend fun getNearRestaurantWithPermission()

    suspend fun getNearRestaurantWithOutPermission()

    suspend fun getNearRestaurantMap(
        page: Int,
        latitude: String,
        longitude: String
    ): Flow<List<VeganMapRestaurant>>

    suspend fun getRestaurantDetail(
        restaurant: Long,
        latitude: String,
        longitude: String
    ): Flow<RestaurantDetail>
}