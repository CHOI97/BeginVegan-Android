package com.example.data.retrofit

import com.example.data.model.core.BaseResponse
import com.example.data.model.veganTest.VeganTypeRequest
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface VeganTypeService {
    @PATCH("/api/v1/users/vegan-type/{type}")
    suspend fun patchVeganType(
        @Header("Authorization") token: String,
        @Path("type") type: String,
        @Body request: VeganTypeRequest
    ): ApiResponse<BaseResponse>
}