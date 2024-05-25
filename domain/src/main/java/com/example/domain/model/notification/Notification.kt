package com.example.domain.model.notification

data class Notification(
    val id: Long, //임시
    val veganType: String,
    val content: String,
    val date: String,
    val checked: Boolean
)
