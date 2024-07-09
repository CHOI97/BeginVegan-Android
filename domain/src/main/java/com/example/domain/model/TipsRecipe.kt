package com.example.domain.model

data class TipsRecipeListItem(
    val id: Int,
    val name: String,
    val veganType: String,
    val isBookmarked: Boolean
)

data class TipsRecipeDetail(
    val id: Int,
    val name: String,
    val veganType: String,
    val ingredients: List<RecipeIngredient>,
    val blocks: List<RecipeBlock>,
    val isBookmarked: Boolean
)

data class RecipeIngredient(
    val id: Int,
    val name: String
)

data class RecipeBlock(
    val content: String,
    val sequence: Int
)