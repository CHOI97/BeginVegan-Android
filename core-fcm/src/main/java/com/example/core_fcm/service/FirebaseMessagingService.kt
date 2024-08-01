package com.example.core_fcm.service

import com.example.core_fcm.useCase.FcmTokenUseCase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseMessagingService:FirebaseMessagingService() {
    @Inject
    lateinit var fcmTokenUseCase: FcmTokenUseCase

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("onNewToken 실행")
        Timber.d("onNewToken : $token")
        GlobalScope.launch(Dispatchers.IO) {
            fcmTokenUseCase.saveToken(token)
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }
}