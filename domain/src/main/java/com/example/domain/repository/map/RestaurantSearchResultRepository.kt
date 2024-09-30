package com.example.domain.repository.map

import com.example.domain.model.map.HistorySearch
import com.example.domain.model.map.VeganMapRestaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantSearchResultRepository {
    suspend fun getRestaurantSearchResult(
        page: Int,
        latitude: String,
        longitude: String
    ): Flow<List<VeganMapRestaurant>>
}