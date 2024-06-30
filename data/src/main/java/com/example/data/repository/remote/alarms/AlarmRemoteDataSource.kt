package com.example.data.repository.remote.alarms

import com.example.data.model.alarms.GetAlarmResponse
import com.example.data.model.alarms.PostAlarmResponse
import com.skydoves.sandwich.ApiResponse

interface AlarmRemoteDataSource {
    suspend fun getAlarms(accessToken:String): ApiResponse<GetAlarmResponse>
    suspend fun postAlarms(): ApiResponse<PostAlarmResponse>
}