package com.example.data.mapper.core

import com.example.data.model.core.BaseResponse
import com.example.domain.mapper.Mapper
import com.example.domain.model.core.BasicResult

class BaseMapper: Mapper<BaseResponse, BasicResult> {
    override fun mapFromEntity(type: BaseResponse): BasicResult {
        return BasicResult(
            check = type.check,
            message = type.information.message
        )
    }
}