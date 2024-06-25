package com.example.data.retrofit

import com.example.data.model.alarms.GetAlarmResponse
import com.example.data.model.alarms.PostAlarmResponse
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AlarmService {

    @GET("/api/v1/alarms")
    suspend fun getAlarms(
        @Header("Authorization") token: String
    ): ApiResponse<GetAlarmResponse>

    @POST("/api/v1/alarms")
    suspend fun postAlarms(
        @Header("Authorization") token: String
    ): ApiResponse<PostAlarmResponse>
}