package com.example.data.repository.remote.alarms

import com.example.data.mapper.alarms.AlarmMapper
import com.example.data.model.alarms.AlarmDto
import com.example.data.model.alarms.AlarmListDto
import com.example.data.model.alarms.GetAlarmResponse
import com.example.data.model.alarms.PostAlarmResponse
import com.example.data.retrofit.AlarmService
import com.example.domain.model.AuthToken
import com.example.domain.model.alarms.Alarm
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AlarmRemoteDataSourceImpl @Inject constructor(
    private val alarmService: AlarmService,
    private val alarmMapper: AlarmMapper
) :AlarmRemoteDataSource {
    override suspend fun getAlarms(): ApiResponse<GetAlarmResponse> {
        return alarmService.getAlarms(AuthToken("","").accessToken).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(true)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

    override suspend fun postAlarms(): ApiResponse<PostAlarmResponse> {
        return alarmService.postAlarms(AuthToken("","").accessToken).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(true)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

    override suspend fun getUnreadAlarms(): Flow<List<Alarm>> = flow {
            alarmService.getAlarms(AuthToken("","").accessToken).suspendOnSuccess {
                Timber.d("SignUp successful")
                ApiResponse.Success(true)
                val unreadList = alarmMapper.fromDtoList(data.information.unreadAlarmResList)
                emit(unreadList)
            }.suspendOnError {
                Timber.e("SignUp error: ${this.errorBody}")
                ApiResponse.Failure.Error(this)
            }

        }

    override suspend fun getReadAlarms(): Flow<List<AlarmDto>> = flow {
        alarmService.getAlarms(AuthToken("","").accessToken).suspendOnSuccess {
            Timber.d("SignUp successful")
            ApiResponse.Success(true).data
            emit(data.information.readAlarmResList)
        }.suspendOnError {
            Timber.e("SignUp error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }
}