package com.example.domain.useCase.alarms

import com.example.domain.model.alarms.AlarmLists
import com.example.domain.repository.alarms.AlarmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnreadAlarmUseCase @Inject constructor(private val alarmRepository: AlarmRepository)  {
    suspend operator fun invoke(accessToken:String): Flow<Result<AlarmLists>> {
        return alarmRepository.getAlarms(accessToken)
    }
}