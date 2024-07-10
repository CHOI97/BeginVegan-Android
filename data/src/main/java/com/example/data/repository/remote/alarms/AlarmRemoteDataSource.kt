package com.example.data.repository.remote.alarms

import com.example.data.model.alarms.GetAlarmResponse
import com.skydoves.sandwich.ApiResponse

interface AlarmRemoteDataSource {
    suspend fun getAlarms(): ApiResponse<GetAlarmResponse>
}