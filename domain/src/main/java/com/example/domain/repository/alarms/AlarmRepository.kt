package com.example.domain.repository.alarms

import com.example.domain.model.alarms.Alarm
import com.example.domain.model.alarms.AlarmLists
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
//    suspend fun getAlarms(): Flow<AlarmLists>
    suspend fun getUnreadAlarmList(): Flow<List<Alarm>>
    suspend fun getReadAlarmList(): Result<Flow<List<Alarm>>>
}