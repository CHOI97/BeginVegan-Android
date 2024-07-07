package com.example.domain.repository.veganType

interface VeganTypeRepository {
    suspend fun patchVeganType(token:String, type:String, veganType: String): Result<Boolean>
}