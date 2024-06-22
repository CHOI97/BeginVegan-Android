package com.example.data.retrofit

import com.example.data.model.auth.AuthRequest
import com.example.data.model.auth.AuthResponse
import com.example.data.model.auth.SignInResponse
import com.example.data.model.auth.SignUpResponse
import com.example.data.model.auth.TokenResponse
import com.skydoves.sandwich.ApiResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface UserService {

    @POST("/auth/sign-up")
    suspend fun signUp(
        @Body request: AuthRequest
    ): ApiResponse<SignUpResponse>

    @POST("/auth/sign-in")
    suspend fun signIn(
        @Body request: AuthRequest
    ):  ApiResponse<SignInResponse>

//    @Multipart
//    @PUT("/auth/sign-up/detail")
//    suspend fun signUpDetail(
//        @Part("nickName") nickName: RequestBody,
//        @Part("veganType") veganType: RequestBody,
//        @Part("isDefaultImage") isDefaultImage: RequestBody,
//        @Part file: MultipartBody.Part?
//    ):  ApiResponse<>

}