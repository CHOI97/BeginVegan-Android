package com.example.presentation.view.tips.viewModel.state

import com.example.domain.model.tips.TipsMagazineItem

data class MagazineListState(
    val response: MutableList<TipsMagazineItem>? = null,
    val isLoading: Boolean = false
)
