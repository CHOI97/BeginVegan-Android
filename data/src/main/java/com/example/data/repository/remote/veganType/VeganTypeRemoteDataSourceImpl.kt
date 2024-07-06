package com.example.data.repository.remote.veganType

import com.example.data.model.auth.SignUpResponse
import com.example.data.model.veganTest.VeganTypeRequest
import com.example.data.retrofit.VeganTypeService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import timber.log.Timber
import javax.inject.Inject

class VeganTypeRemoteDataSourceImpl @Inject constructor(
    private val veganTypeService: VeganTypeService
) : VeganTypeRemoteDataSource {

    override suspend fun patchVeganType(token:String, type:String, veganTypeRequest: VeganTypeRequest): ApiResponse<SignUpResponse> {
        return veganTypeService.patchVeganType(token, type, veganTypeRequest).suspendOnSuccess {
            Timber.d("PatchVeganType successful")
            ApiResponse.Success(true)
        }.suspendOnError {
            Timber.e("PatchVeganType error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }
}