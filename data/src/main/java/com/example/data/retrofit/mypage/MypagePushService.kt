package com.example.data.retrofit.mypage

import com.example.data.model.mypage.MypagePushResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Header
import retrofit2.http.PATCH

interface MypagePushService {
    @PATCH("/api/v1/users/alarm")
    suspend fun patchPush(
        @Header("Authorization") token: String
    ): ApiResponse<MypagePushResponse>
}