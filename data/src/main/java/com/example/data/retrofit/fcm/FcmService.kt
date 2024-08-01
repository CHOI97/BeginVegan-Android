package com.example.data.retrofit.fcm

import com.example.data.model.core.BaseResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface FcmService {
    @PATCH("/api/v1/users/fcm/token")
    suspend fun patchFcmToken(
        @Header("Authorization") token: String,
        @Body fcmToken: String
    ): ApiResponse<BaseResponse>
}