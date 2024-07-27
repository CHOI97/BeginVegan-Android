package com.example.presentation.view.mypage.viewModel.state

import com.example.domain.model.tips.TipsRecipeListItem

data class MyRecipeState(
    val response: MutableList<TipsRecipeListItem>? = null,
    val isLoading: Boolean = false
)
