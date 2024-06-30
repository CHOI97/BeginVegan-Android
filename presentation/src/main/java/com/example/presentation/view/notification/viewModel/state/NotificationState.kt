package com.example.presentation.view.notification.viewModel.state

import com.example.domain.model.alarms.AlarmLists

data class NotificationState(
    val response: AlarmLists? = null,
    val isLoading: Boolean = false
)
