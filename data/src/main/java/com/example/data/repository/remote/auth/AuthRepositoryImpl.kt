package com.example.data.repository.remote.auth

import com.example.data.mapper.auth.AuthMapper
import com.example.data.model.auth.AuthRequest
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.domain.model.auth.AuthToken
import com.example.domain.repository.auth.AuthRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authTokenDataSource: AuthTokenDataSource,
    private val authMapper: AuthMapper
) : AuthRepository {

    override suspend fun signIn(email: String, providerId: String): Result<AuthToken> {
        return try {
            val authRequest = AuthRequest(email = email, providerId = providerId)
            when (val response = authRemoteDataSource.signIn(authRequest)) {
                is ApiResponse.Success -> {
                    authTokenDataSource.saveTokens(response.data.information.authRes.accessToken,response.data.information.authRes.refreshToken)
                    val authToken = authMapper.mapFromEntity(response.data.information)
                    Result.success(authToken)
                }
                is ApiResponse.Failure.Error -> {
                    Timber.e("SignIn error: ${response.errorBody}")
                    Result.failure(Exception("SignIn failed"))
                }
                is ApiResponse.Failure.Exception -> {
                    Timber.e("SignIn exception: ${response.message}")
                    Result.failure(response.throwable)
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "SignIn exception")
            Result.failure(e)
        }
    }
}
