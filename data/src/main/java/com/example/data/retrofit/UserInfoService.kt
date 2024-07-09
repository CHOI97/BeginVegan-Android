package com.example.data.retrofit

import com.example.data.model.core.BaseResponse
import com.example.data.model.userInfo.AddUserInfoReq
import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface UserInfoService {
    @Multipart
    @PUT("/auth/sign-up/detail")
    suspend fun updateUserInfo(
        @Header("Authorization") token: String,
        @Part("addUserInfoReq") addUserInfoReq: AddUserInfoReq,
        @Part("isDefaultImage") isDefaultImage: Boolean,
        @Part file: MultipartBody.Part?
    ):ApiResponse<BaseResponse>
}