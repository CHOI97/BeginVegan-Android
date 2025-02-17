package com.example.data.mapper.tips

import com.example.data.model.tips.RecipeBlockDto
import com.example.data.model.tips.RecipeIngredientDto
import com.example.data.model.tips.TipsRecipeDetailDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.tips.RecipeBlock
import com.example.domain.model.tips.RecipeIngredient
import com.example.domain.model.tips.TipsRecipeDetail

class TipsRecipeDetailMapper:Mapper<TipsRecipeDetailDto, TipsRecipeDetail> {
    override fun mapFromEntity(type: TipsRecipeDetailDto): TipsRecipeDetail = TipsRecipeDetail(
        id = type.id,
        name = type.name,
        veganType = type.veganType,
        ingredients = mapIngredientEntity(type.ingredients),
        blocks = mapBlockEntity(type.blocks),
        isBookmarked = type.isBookmarked
    )

    private fun mapIngredientEntity(ingredientsList: List<RecipeIngredientDto>):List<RecipeIngredient>{
        return ingredientsList.map {
            RecipeIngredient(id = it.id, name = it.name)
        }
    }

    private fun mapBlockEntity(blockList: List<RecipeBlockDto>) : List<RecipeBlock>{
        return blockList.map{
            RecipeBlock(it.content, it.sequence)
        }
    }
}