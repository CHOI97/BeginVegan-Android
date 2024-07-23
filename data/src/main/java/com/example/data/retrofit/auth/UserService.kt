package com.example.data.retrofit.auth

import com.example.data.model.auth.AuthRequest
import com.example.data.model.auth.SignInResponse
import com.example.data.model.core.BaseResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/auth/sign-in")
    suspend fun signIn(
        @Body request: AuthRequest
    ):  ApiResponse<SignInResponse>
}