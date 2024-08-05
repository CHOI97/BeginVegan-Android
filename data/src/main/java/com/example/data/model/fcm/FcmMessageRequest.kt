package com.example.data.model.fcm

data class FcmMessageRequest(
    val title:String,
    val body:String,
    val alarmType:String?,
    val itemId:Int?,
    val messageType:String?,
    val userLevel:String?
)
