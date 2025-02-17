package com.example.data.repository.remote.tips

import com.example.data.model.tips.TipsRecipeDetailResponse
import com.example.data.model.tips.TipsRecipeListResponse
import com.skydoves.sandwich.ApiResponse

interface TipsRecipeRemoteDataSource {
    suspend fun getRecipeList(page:Int): ApiResponse<TipsRecipeListResponse>
    suspend fun getRecipeDetail(id:Int): ApiResponse<TipsRecipeDetailResponse>
    suspend fun getRecipeMy(page:Int): ApiResponse<TipsRecipeListResponse>
    suspend fun getHomeRecipe(): ApiResponse<TipsRecipeListResponse>
}