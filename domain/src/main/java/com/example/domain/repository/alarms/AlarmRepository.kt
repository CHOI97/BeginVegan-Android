package com.example.domain.repository.alarms

import com.example.domain.model.alarms.AlarmLists
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    suspend fun getAlarms(): Flow<Result<AlarmLists>>
}