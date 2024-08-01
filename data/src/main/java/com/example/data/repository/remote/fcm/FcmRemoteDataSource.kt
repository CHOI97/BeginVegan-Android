package com.example.data.repository.remote.fcm

import com.example.data.model.core.BaseResponse
import com.skydoves.sandwich.ApiResponse

interface FcmRemoteDataSource {
    suspend fun patchFcmToken(token:String): ApiResponse<BaseResponse>
}