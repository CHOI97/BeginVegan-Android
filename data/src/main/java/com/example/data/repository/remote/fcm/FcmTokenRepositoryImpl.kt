package com.example.data.repository.remote.fcm

import com.example.core_fcm.repository.FcmTokenRepository
import javax.inject.Inject

class FcmTokenRepositoryImpl @Inject constructor(
    private val fcmRemoteDataSource: FcmRemoteDataSource
):FcmTokenRepository {
    override suspend fun saveToken(token: String) {
        fcmRemoteDataSource.patchFcmToken(token)
    }
}