package com.example.domain.useCase.map.restaurant

import com.example.domain.repository.map.VeganMapRepository
import javax.inject.Inject

class GetNearRestaurantUseCase @Inject constructor(
    private val veganMapRepository: VeganMapRepository
){
    suspend fun getNearRestaurantWithPermission() = veganMapRepository.getNearRestaurantWithPermission()

    suspend fun getNearRestaurantWithOutPermission() = veganMapRepository.getNearRestaurantWithOutPermission()
}