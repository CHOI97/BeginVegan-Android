package com.example.data.repository

import com.example.data.model.auth.AuthRequest
import com.example.data.model.common.ErrorResponse
import com.example.data.model.common.SuccessResponse
import com.example.data.retrofit.UserService
import com.example.domain.repository.auth.AuthRepository
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val userService: UserService) : AuthRepository {
    override suspend fun signUp(email: String, providerId: String): Result<Boolean> {
        return try {
            when (val response = userService.signUp(AuthRequest(email, providerId))) {
                is SuccessResponse -> {
                    Timber.d("response = $response")
                    Result.success(true)
                }
                is ErrorResponse -> {
                    Timber.d("response = $response")
                    Result.failure(Exception(response.information.message))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signIn(email: String, providerId: String): Result<Boolean> {
        return try {
            when (val response = userService.signIn(AuthRequest(email, providerId))) {
                is SuccessResponse -> {
                    Timber.d("response = $response")
                    Result.success(true)
                }
                is ErrorResponse -> {
                    Timber.d("response = $response")
                    Result.failure(Exception(response.information.message))
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}