package com.example.data.repository.remote.tips

import com.example.data.model.tips.TipsRecipeDetailResponse
import com.example.data.model.tips.TipsRecipeListResponse
import com.skydoves.sandwich.ApiResponse

interface TipsRecipeRemoteDataSource {
    suspend fun getRecipeList(token:String, page:Int): ApiResponse<TipsRecipeListResponse>
    suspend fun getRecipeDetail(token:String, id:Int): ApiResponse<TipsRecipeDetailResponse>
    suspend fun getRecipeMy(token:String, page:Int): ApiResponse<TipsRecipeListResponse>
    suspend fun getHomeRecipe(token:String): ApiResponse<TipsRecipeListResponse>
}