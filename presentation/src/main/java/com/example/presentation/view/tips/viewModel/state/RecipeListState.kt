package com.example.presentation.view.tips.viewModel.state

import com.example.domain.model.TipsRecipeListItem

data class RecipeListState(
    val response: MutableList<TipsRecipeListItem>? = null,
    val isLoading: Boolean = false
)
