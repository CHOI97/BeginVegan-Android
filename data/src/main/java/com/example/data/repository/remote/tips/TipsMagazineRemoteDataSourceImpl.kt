package com.example.data.repository.remote.tips

import com.example.data.model.tips.MagazineDetailResponse
import com.example.data.model.tips.MagazineResponse
import com.example.data.retrofit.TipsMagazineService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import timber.log.Timber
import javax.inject.Inject

class TipsMagazineRemoteDataSourceImpl @Inject constructor(
    private val tipsMagazineService: TipsMagazineService
) : TipsMagazineRemoteDataSource {
    override suspend fun getMagazineList(
        accessToken: String,
        page: Int
    ): ApiResponse<MagazineResponse> {
        return tipsMagazineService.getMagazineList(accessToken, page).suspendOnSuccess {
            Timber.d("GetMagazineList successful")
            ApiResponse.Success(this.data)
        }.suspendOnError {
            Timber.e("GetMagazineList error: ${this.errorBody}")
            ApiResponse.Failure.Error(this.errorBody)
        }
    }

    override suspend fun getMagazineDetail(
        accessToken: String,
        id: Int
    ): ApiResponse<MagazineDetailResponse> {
        return tipsMagazineService.getMagazineDetail(accessToken, id).suspendOnSuccess {
            Timber.d("GetMagazineDetail successful")
            ApiResponse.Success(this.data)
        }.suspendOnError {
            Timber.e("GetMagazineDetail error: ${this.errorBody}")
            ApiResponse.Failure.Error(this.errorBody)
        }
    }

    override suspend fun getHomeMagazine(accessToken: String): ApiResponse<MagazineResponse> {
        return tipsMagazineService.getHomeMagazine(accessToken).suspendOnSuccess {
            Timber.d("GetHomeMagazine successful")
            ApiResponse.Success(this.data)
        }.suspendOnError {
            Timber.e("GetHomeMagazine error: ${this.errorBody}")
            ApiResponse.Failure.Error(this.errorBody)
        }
    }

}