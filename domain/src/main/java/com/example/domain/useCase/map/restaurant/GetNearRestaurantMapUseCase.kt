package com.example.domain.useCase.map.restaurant

import com.example.domain.repository.map.VeganMapRepository
import javax.inject.Inject

class GetNearRestaurantMapUseCase @Inject constructor(
    private val veganMapRepository: VeganMapRepository
){
    suspend operator fun invoke() = veganMapRepository.getNearRestaurantMap()
}
