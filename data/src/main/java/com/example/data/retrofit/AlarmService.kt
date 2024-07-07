package com.example.data.retrofit

import com.example.data.model.alarms.GetAlarmResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface AlarmService {

    @GET("/api/v1/alarms")
    suspend fun getAlarms(
        @Header("Authorization") token: String
    ): ApiResponse<GetAlarmResponse>

}