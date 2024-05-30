package com.example.data.repository.remote.auth

import com.example.data.model.auth.AuthRequest
import com.example.data.model.common.ApiResponse
import com.example.data.model.common.ErrorResponse
import com.example.data.model.common.SuccessResponse
import com.example.data.retrofit.UserService
import timber.log.Timber
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : AuthRemoteDataSource {
    override suspend fun signUp(authRequest: AuthRequest): Result<ApiResponse> {
        return try {
            val response = userService.signUp(authRequest)
            when (response) {
                is SuccessResponse<*> -> {
                    Timber.d("response = $response")
                    Result.success(response)
                }
                is ErrorResponse -> {
                    Timber.d("response = $response")
                    Result.failure(Exception(response.information.message))
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }

    override suspend fun signIn(authRequest: AuthRequest): Result<ApiResponse> {
        return try {
            val response = userService.signIn(authRequest)
            when (response) {
                is SuccessResponse<*> -> {
                    Timber.d("response = $response")
                    Result.success(response)
                }
                is ErrorResponse -> {
                    Timber.d("response = $response")
                    Result.failure(Exception(response.information.message))
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }
}
