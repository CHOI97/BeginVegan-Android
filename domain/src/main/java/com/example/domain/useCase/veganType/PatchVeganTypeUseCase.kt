package com.example.domain.useCase.veganType

import com.example.domain.repository.veganType.VeganTypeRepository
import javax.inject.Inject

class PatchVeganTypeUseCase @Inject constructor(private val veganTypeRepository: VeganTypeRepository) {
    suspend operator fun invoke(token:String, type:String, veganType:String): Result<Boolean> = veganTypeRepository.patchVeganType(token, type, veganType)
}