package com.example.data.repository.remote.alarms

import com.example.data.model.alarms.GetAlarmResponse
import com.example.data.model.alarms.PostAlarmResponse
import com.example.data.retrofit.AlarmService
import com.example.domain.model.AuthToken
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import timber.log.Timber
import javax.inject.Inject

class AlarmRemoteDataSourceImpl @Inject constructor(
    private val alarmService: AlarmService
) :AlarmRemoteDataSource {
    override suspend fun getAlarms(accessToken:String): ApiResponse<GetAlarmResponse> {
        return alarmService.getAlarms(accessToken).suspendOnSuccess {
            Timber.d("GetAlarms successful")
            ApiResponse.Success(this.data)
        }.suspendOnError {
            Timber.e("GetAlarms error: ${this.errorBody}")
            ApiResponse.Failure.Error(this.errorBody)
        }
    }


    override suspend fun postAlarms(): ApiResponse<PostAlarmResponse> {
        return alarmService.postAlarms(AuthToken("","").accessToken).suspendOnSuccess {
            Timber.d("GetAlarms successful")
            ApiResponse.Success(true)
        }.suspendOnError {
            Timber.e("GetAlarms error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }
}