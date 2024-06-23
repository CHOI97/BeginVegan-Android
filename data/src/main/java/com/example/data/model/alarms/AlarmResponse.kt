package com.example.data.model.alarms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class AlarmResponse (
    @Json(name = "alarmId")
    val alarmId: Int,
    @Json(name = "alarmYype")
    val alarmType: String,
    @Json(name = "content")
    val content: String,
    @Json(name = "itemId")
    val itemId: Int,
    @Json(name = "createdDate")
    val createdDate: Date,
    @Json(name = "isRead")
    val isRead: Boolean
)