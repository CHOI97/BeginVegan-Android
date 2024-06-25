package com.example.domain.model.alarms

import java.util.Date
import java.util.concurrent.Flow

data class AlarmLists(
    val unreadAlarmList: List<Alarm>,
    val readAlarmList: List<Alarm>
)

data class Alarm(
    val alarmId: Int,
    val alarmType: String,
    val content: String,
    val itemId: Int,
    val createdDate: Date
)
