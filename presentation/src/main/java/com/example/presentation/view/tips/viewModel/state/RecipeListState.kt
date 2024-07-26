package com.example.presentation.view.tips.viewModel.state

import com.example.domain.model.tips.TipsRecipeListItem

data class RecipeListState(
    val response: MutableList<TipsRecipeListItem> = mutableListOf(),
    val isLoading: Boolean = false
)
