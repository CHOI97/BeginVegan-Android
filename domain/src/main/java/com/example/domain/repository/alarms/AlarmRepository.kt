package com.example.domain.repository.alarms

import com.example.domain.model.alarms.AlarmLists
import kotlinx.coroutines.flow.Flow

interface AlarmRepository {
    suspend fun getAlarms(accessToken:String): Flow<Result<AlarmLists>>
}