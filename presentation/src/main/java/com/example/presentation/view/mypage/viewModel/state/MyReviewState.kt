package com.example.presentation.view.mypage.viewModel.state

import com.example.domain.model.mypage.MyReview

data class MyReviewState(
    val response: List<MyReview>? = null,
    val isLoading: Boolean = false
)
