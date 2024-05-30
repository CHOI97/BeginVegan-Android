package com.example.data.repository.remote.auth

import com.example.data.mapper.auth.AuthMapper
import com.example.data.model.auth.AuthRequest
import com.example.data.model.auth.TokenResponse
import com.example.data.model.common.SuccessResponse
import com.example.domain.model.AuthToken
import com.example.domain.repository.auth.AuthRepository
import timber.log.Timber
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authMapper: AuthMapper
) : AuthRepository {
    override suspend fun signUp(email: String, providerId: String): Boolean {
        return try {
            val response = authRemoteDataSource.signUp(AuthRequest(email, providerId))
            response.map { true }
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }

    override suspend fun signIn(email: String, providerId: String): Result<AuthToken> {
        return try {
            val response = authRemoteDataSource.signIn(AuthRequest(email, providerId))
            response.mapCatching { apiResponse ->
                val successResponse = apiResponse as SuccessResponse<TokenResponse>
                authMapper.mapFromEntity(successResponse.information)
            }
        } catch (e: Exception) {
            Timber.e(e)
            Result.failure(e)
        }
    }
}