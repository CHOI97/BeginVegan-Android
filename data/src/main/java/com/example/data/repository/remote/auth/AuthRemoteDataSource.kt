package com.example.data.repository.remote.auth

import com.example.data.model.auth.AuthRequest
import com.example.data.model.common.ApiResponse

interface AuthRemoteDataSource {
    suspend fun signUp(authRequest: AuthRequest): Result<ApiResponse>
    suspend fun signIn(authRequest: AuthRequest): Result<ApiResponse>
}