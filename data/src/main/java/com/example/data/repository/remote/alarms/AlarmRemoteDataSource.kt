package com.example.data.repository.remote.alarms

import com.example.data.model.alarms.AlarmDto
import com.example.data.model.alarms.AlarmListDto
import com.example.data.model.alarms.GetAlarmResponse
import com.example.data.model.alarms.PostAlarmResponse
import com.example.data.retrofit.AlarmService
import com.example.domain.model.alarms.Alarm
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AlarmRemoteDataSource {
    suspend fun getAlarms(): ApiResponse<GetAlarmResponse>
    suspend fun postAlarms(): ApiResponse<PostAlarmResponse>

    suspend fun getUnreadAlarms(): Flow<List<Alarm>>

    suspend fun getReadAlarms(): Flow<List<AlarmDto>>
}