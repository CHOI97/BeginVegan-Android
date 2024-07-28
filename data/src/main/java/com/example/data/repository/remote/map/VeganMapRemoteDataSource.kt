package com.example.data.repository.remote.map

import com.example.data.model.map.VeganMapRestaurantResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow

interface VeganMapRemoteDataSource {
    suspend fun getNearRestaurantMap(page: Int, latitude: String, longitude: String): ApiResponse<VeganMapRestaurantResponse>
}