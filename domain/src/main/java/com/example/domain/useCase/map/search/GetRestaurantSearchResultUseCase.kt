package com.example.domain.useCase.map.search

import com.example.domain.model.map.VeganMapRestaurant
import com.example.domain.repository.map.HistorySearchRepository
import com.example.domain.repository.map.RestaurantSearchResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRestaurantSearchResultUseCase @Inject constructor(private val restaurantSearchResultRepository: RestaurantSearchResultRepository) {
    suspend operator fun invoke(
        page: Int,
        latitude: String,
        longitude: String
    ): Flow<List<VeganMapRestaurant>> =
        restaurantSearchResultRepository.getRestaurantSearchResult(page, latitude, longitude)
}