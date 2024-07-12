package com.example.presentation.view.mypage.viewModel.state

import com.example.domain.model.mypage.MypageMyRecipeItem

data class MyRecipeState(
    val response: List<MypageMyRecipeItem>? = null,
    val isLoading: Boolean = false
)
