package com.example.data.mapper.core

import com.example.data.model.auth.SignInResponse
import com.example.data.model.core.BaseResponse
import com.example.domain.mapper.Mapper
import com.example.domain.model.core.OperationResult

class BaseMapper: Mapper<BaseResponse, OperationResult> {
    override fun mapFromEntity(type: BaseResponse): OperationResult {
        return OperationResult(
            check = type.check,
            message = type.information.message
        )
    }
}