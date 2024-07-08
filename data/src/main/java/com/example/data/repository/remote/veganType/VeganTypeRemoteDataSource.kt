package com.example.data.repository.remote.veganType

import com.example.data.model.core.BaseResponse
import com.example.data.model.veganTest.VeganTypeRequest
import com.skydoves.sandwich.ApiResponse

interface VeganTypeRemoteDataSource {
    suspend fun patchVeganType(token:String, type:String, veganTypeRequest: VeganTypeRequest): ApiResponse<BaseResponse>
}