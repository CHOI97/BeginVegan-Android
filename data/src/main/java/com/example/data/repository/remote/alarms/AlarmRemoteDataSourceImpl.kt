package com.example.data.repository.remote.alarms

import com.example.data.model.alarms.GetAlarmResponse
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.retrofit.AlarmService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class AlarmRemoteDataSourceImpl @Inject constructor(
    private val alarmService: AlarmService,
    private val authTokenDataSource: AuthTokenDataSource
) :AlarmRemoteDataSource {
    override suspend fun getAlarms(): ApiResponse<GetAlarmResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return alarmService.getAlarms(authHeader).suspendOnSuccess {
            Timber.d("GetAlarms successful")
            ApiResponse.Success(this.data)
        }.suspendOnError {
            Timber.e("GetAlarms error: ${this.errorBody}")
            ApiResponse.Failure.Error(this.errorBody)
        }
    }

}