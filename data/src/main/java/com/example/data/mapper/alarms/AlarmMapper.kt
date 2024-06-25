package com.example.data.mapper.alarms

import com.example.data.model.alarms.AlarmDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.alarms.Alarm

class AlarmMapper:Mapper<AlarmDto, Alarm> {
    override fun mapFromEntity(type: AlarmDto): Alarm {
        return Alarm(
            alarmId = type.alarmId,
            alarmType = type.alarmType,
            content = type.content,
            itemId = type.itemId,
            createdDate = type.createdDate
        )
    }

    fun fromDtoList(dtoList: List<AlarmDto>):List<Alarm>{
        return dtoList.map { mapFromEntity(it) }
    }
}