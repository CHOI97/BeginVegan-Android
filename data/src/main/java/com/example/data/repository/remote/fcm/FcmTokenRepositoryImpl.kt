package com.example.data.repository.remote.fcm

import com.example.core_fcm.repository.FcmTokenRepository
import com.example.data.model.bookmarks.BookmarkRequest
import com.example.data.model.fcm.FcmMessageRequest
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import timber.log.Timber
import javax.inject.Inject

class FcmTokenRepositoryImpl @Inject constructor(
    private val fcmRemoteDataSource: FcmRemoteDataSource
): FcmTokenRepository {
    override suspend fun getHasFcmToken(): Result<Boolean> {
        return try {
            val response = fcmRemoteDataSource.getHasFcmToken()
            when (response) {
                is ApiResponse.Success -> {
                    Result.success(response.data.information.storedFcmToken)
                }
                is ApiResponse.Failure.Error -> {
                    Timber.e("getHasFcmToken error: ${response.errorBody}")
                    Result.failure(Exception("getHasFcmToken failed"))
                }
                is ApiResponse.Failure.Exception -> {
                    Timber.e("getHasFcmToken exception: ${response.message}")
                    Result.failure(response.throwable)
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "getHasFcmToken exception")
            Result.failure(e)
        }
    }

    override suspend fun saveToken(token: String) {
        fcmRemoteDataSource.patchFcmToken(token)
    }

    override suspend fun postFcmMessage(
        title: String,
        body: String,
        alarmType: String?,
        itemId: Int?,
        messageType: String?,
        userLevel: String?
    ) {
        val requestBody = FcmMessageRequest(title, body, alarmType, itemId, messageType, userLevel)
        fcmRemoteDataSource.postFcmMessage(requestBody)
    }
}