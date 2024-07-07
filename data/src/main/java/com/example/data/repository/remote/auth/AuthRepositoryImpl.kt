package com.example.data.repository.remote.auth

import com.example.data.mapper.auth.AuthMapper
import com.example.data.model.auth.AuthRequest
import com.example.domain.model.auth.AuthToken
import com.example.domain.repository.auth.AuthRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authMapper: AuthMapper
) : AuthRepository {
//    override suspend fun signUp(email: String, providerId: String): Result<Boolean> {
//        return try {
//            val authRequest = AuthRequest(email = email, providerId = providerId)
//            val response = authRemoteDataSource.signUp(authRequest)
//            when (response) {
//                is ApiResponse.Success -> Result.success(response.data.check)
//                is ApiResponse.Failure.Error -> {
//                    Timber.e("SignUp error: ${response.errorBody}")
//                    Result.failure(Exception("SignUp failed"))
//                }
//                is ApiResponse.Failure.Exception -> {
//                    Timber.e("SignUp exception: ${response.message}")
//                    Result.failure(response.throwable)
//                }
//            }
//        } catch (e: Exception) {
//            Timber.e(e, "SignUp exception")
//            Result.failure(e)
//        }
//    }

    override suspend fun signIn(email: String, providerId: String): Result<AuthToken> {
        return try {
            val authRequest = AuthRequest(email = email, providerId = providerId)
            val response = authRemoteDataSource.signIn(authRequest)
            when (response) {
                is ApiResponse.Success -> {
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
