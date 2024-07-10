package com.example.data.repository.remote.tips

import com.example.data.model.tips.TipsRecipeDetailResponse
import com.example.data.model.tips.TipsRecipeListResponse
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.retrofit.TipsRecipeService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class TipsRecipeRemoteDataSourceImpl @Inject constructor(
    private val tipsRecipeService: TipsRecipeService,
    private val authTokenDataSource: AuthTokenDataSource
) : TipsRecipeRemoteDataSource {

    override suspend fun getRecipeList(page: Int): ApiResponse<TipsRecipeListResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return tipsRecipeService.getRecipeList(authHeader, page).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

    override suspend fun getRecipeDetail(id: Int): ApiResponse<TipsRecipeDetailResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return tipsRecipeService.getRecipeDetail(authHeader, id).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

    override suspend fun getRecipeMy(
        page: Int
    ): ApiResponse<TipsRecipeListResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return tipsRecipeService.getRecipeMy(authHeader, page).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

    override suspend fun getHomeRecipe(): ApiResponse<TipsRecipeListResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return tipsRecipeService.getHomeRecipe(authHeader).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

}