package com.example.domain.useCase.tips

import com.example.domain.model.tips.TipsRecipeDetail
import com.example.domain.model.tips.TipsRecipeListItem
import com.example.domain.repository.tips.TipsRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TipsRecipeUseCase @Inject constructor(
    private val tipsRecipeRepository: TipsRecipeRepository
) {
    suspend fun getRecipeList(page:Int): Flow<Result<List<TipsRecipeListItem>>> =
        tipsRecipeRepository.getRecipeList(page)

    suspend fun getRecipeDetail(id:Int): Result<TipsRecipeDetail> =
        tipsRecipeRepository.getRecipeDetail(id)

    suspend fun getRecipeMy(page:Int): Flow<Result<List<TipsRecipeListItem>>> =
        tipsRecipeRepository.getRecipeForMe(page)

    suspend fun getHomeRecipe(): Result<List<TipsRecipeListItem>> =
        tipsRecipeRepository.getHomeRecipe()

}