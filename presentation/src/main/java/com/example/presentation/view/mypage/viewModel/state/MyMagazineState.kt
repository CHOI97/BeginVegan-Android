package com.example.presentation.view.mypage.viewModel.state

import com.example.domain.model.alarms.AlarmLists
import com.example.domain.model.mypage.MypageMyMagazineItem

data class MyMagazineState(
    val response: List<MypageMyMagazineItem>? = null,
    val isLoading: Boolean = false
)
