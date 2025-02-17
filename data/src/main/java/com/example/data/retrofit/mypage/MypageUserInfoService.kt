package com.example.data.retrofit.mypage

import com.example.data.model.mypage.MypageUserInfoResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface MypageUserInfoService {
    @GET("/api/v1/users/my-page")
    suspend fun getMypageUserInfo(
        @Header("Authorization") token: String
    ): ApiResponse<MypageUserInfoResponse>
}