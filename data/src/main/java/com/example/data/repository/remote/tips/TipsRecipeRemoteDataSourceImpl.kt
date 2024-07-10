package com.example.data.repository.remote.tips

import com.example.data.model.tips.TipsRecipeDetailResponse
import com.example.data.model.tips.TipsRecipeListResponse
import com.example.data.retrofit.TipsRecipeService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import timber.log.Timber
import javax.inject.Inject

class TipsRecipeRemoteDataSourceImpl @Inject constructor(
    private val tipsRecipeService: TipsRecipeService
):TipsRecipeRemoteDataSource {
    override suspend fun getRecipeList(token:String, page:Int): ApiResponse<TipsRecipeListResponse> {
        return tipsRecipeService.getRecipeList(token, page).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

    override suspend fun getRecipeDetail(token:String, id:Int): ApiResponse<TipsRecipeDetailResponse> {
        return tipsRecipeService.getRecipeDetail(token, id).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

    override suspend fun getRecipeMy(token:String, page:Int): ApiResponse<TipsRecipeListResponse> {
        return tipsRecipeService.getRecipeMy(token, page).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

    override suspend fun getHomeRecipe(token:String): ApiResponse<TipsRecipeListResponse> {
        return tipsRecipeService.getHomeRecipe(token).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

}