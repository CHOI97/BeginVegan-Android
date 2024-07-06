package com.example.domain.useCase.tips

import com.example.domain.model.TipsRecipeDetail
import com.example.domain.model.TipsRecipeListItem
import com.example.domain.repository.tips.TipsRecipeRepository
import javax.inject.Inject

class TipsRecipeUseCase @Inject constructor(
    private val tipsRecipeRepository: TipsRecipeRepository
) {
    suspend fun getRecipeList(accessToken:String, page:Int): Result<List<TipsRecipeListItem>> =
        tipsRecipeRepository.getRecipeList(accessToken, page)

    suspend fun getRecipeDetail(accessToken:String, id:Int): Result<TipsRecipeDetail> =
        tipsRecipeRepository.getRecipeDetail(accessToken, id)

    suspend fun getRecipeMy(accessToken:String, page:Int): Result<List<TipsRecipeListItem>> =
        tipsRecipeRepository.getRecipeForMe(accessToken, page)

    suspend fun getHomeRecipe(accessToken:String): Result<List<TipsRecipeListItem>> =
        tipsRecipeRepository.getHomeRecipe(accessToken)

}