package com.example.presentation.view.mypage.viewModel.state

import com.example.domain.model.mypage.MypageMyRestaurantItem

data class MyRestaurantState(
    val response: List<MypageMyRestaurantItem>? = null,
    val isLoading: Boolean = false
)
