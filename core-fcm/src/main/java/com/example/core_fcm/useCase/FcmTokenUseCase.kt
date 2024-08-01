package com.example.core_fcm.useCase

import com.example.core_fcm.repository.FcmTokenRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FcmTokenUseCase @Inject constructor(
    private val fcmTokenRepository: FcmTokenRepository
) {
    suspend fun saveToken(token: String) {
        fcmTokenRepository.saveToken(token)
    }
}