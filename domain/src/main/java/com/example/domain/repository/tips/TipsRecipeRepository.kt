package com.example.domain.repository.tips

import com.example.domain.model.TipsRecipeDetail
import com.example.domain.model.TipsRecipeListItem
import kotlinx.coroutines.flow.Flow

interface TipsRecipeRepository {
    suspend fun getRecipeList(accessToken:String, page:Int): Flow<Result<List<TipsRecipeListItem>>>
    suspend fun getRecipeDetail(accessToken:String, id:Int): Result<TipsRecipeDetail>
    suspend fun getRecipeForMe(accessToken:String, page:Int): Result<List<TipsRecipeListItem>>
    suspend fun getHomeRecipe(accessToken:String): Result<List<TipsRecipeListItem>>
}