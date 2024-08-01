package com.example.core_fcm.useCase

import com.example.core_fcm.repository.FcmTokenRepository
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FcmTokenUseCase @Inject constructor(
    private val fcmTokenRepository: FcmTokenRepository
) {
    fun test(){
        Timber.d("FirebaseMessaging.getInstance().token: ${FirebaseMessaging.getInstance().token}")
    }

    suspend fun saveToken(token: String) {
        fcmTokenRepository.saveToken(token)
    }
}