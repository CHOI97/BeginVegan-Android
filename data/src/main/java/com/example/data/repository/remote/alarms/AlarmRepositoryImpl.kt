package com.example.data.repository.remote.alarms

import com.example.data.mapper.alarms.AlarmMapper
import com.example.data.model.auth.AuthRequest
import com.example.domain.model.alarms.Alarm
import com.example.domain.model.alarms.AlarmLists
import com.example.domain.repository.alarms.AlarmRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmRemoteDataSource: AlarmRemoteDataSource,
    private val alarmMapper: AlarmMapper
):AlarmRepository {
//    override suspend fun getAlarms(): Result<Flow<AlarmLists>> {
//        return try{
////            flow{
////                val response = alarmRemoteDataSource.getAlarms()
////                emit(response)
////            }
//            flow{
//                val response = alarmRemoteDataSource.getAlarms()
//                when(response) {
//                    is ApiResponse.Success -> {
//                        val unreadAlarmList = alarmMapper.fromDtoList(response.data.information.unreadAlarmResList)
//                        val readAlarmList = alarmMapper.fromDtoList(response.data.information.readAlarmResList)
//                        val newAlarmList = AlarmLists(unreadAlarmList, readAlarmList)
//                        Result.success(emit(newAlarmList))
//                    }
//                    is ApiResponse.Failure.Error -> {
//                        Timber.e("SignUp error: ${response.errorBody}")
//                        Result.failure(Exception("SignUp failed"))
//                    }
//                    is ApiResponse.Failure.Exception -> {
//                        Timber.e("SignUp exception: ${response.message}")
//                        Result.failure(response.throwable)
//                    }
//                }
//            }
//        }catch (e: Exception) {
//            Timber.e(e, "SignUp exception")
//            Result.failure(e)
//        }
//
//    }

    override suspend fun getUnreadAlarmList(): Flow<List<Alarm>> {
        return  alarmRemoteDataSource.getUnreadAlarms()
    }

    override suspend fun getReadAlarmList(): Result<Flow<List<Alarm>>> {
        return try {
            Result.success(
                flow {
                    alarmRemoteDataSource.getReadAlarms().collect {
                        (alarmMapper.fromDtoList(it))
                    }
                }
            )
        }catch (e: Exception) {
            Timber.e(e, "SignIn exception")
            Result.failure(e)
        }
    }
}