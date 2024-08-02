package com.example.domain.useCase.map.restaurant

import com.example.domain.model.map.RestaurantDetail
import com.example.domain.model.map.VeganMapRestaurant
import com.example.domain.repository.map.VeganMapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRestaurantDetailUseCase @Inject constructor(
    private val veganMapRepository: VeganMapRepository
) {
    suspend operator fun invoke(
        restaurantId: Long,
        latitude: String,
        longitude: String
    ): Flow<RestaurantDetail> =
        veganMapRepository.getRestaurantDetail(
            restaurantId,
            latitude,
            longitude
        )
}
