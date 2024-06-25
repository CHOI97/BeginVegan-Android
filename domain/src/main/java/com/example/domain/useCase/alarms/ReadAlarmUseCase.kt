package com.example.domain.useCase.alarms

import com.example.domain.repository.alarms.AlarmRepository
import javax.inject.Inject

class ReadAlarmUseCase @Inject constructor(private val alarmRepository: AlarmRepository)  {
    suspend operator fun invoke() = alarmRepository.getUnreadAlarmList()
}