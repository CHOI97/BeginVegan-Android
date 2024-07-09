package com.example.data.repository.remote.tips

import com.example.data.model.tips.MagazineDetailResponse
import com.example.data.model.tips.MagazineResponse
import com.skydoves.sandwich.ApiResponse

interface TipsMagazineRemoteDataSource {
    suspend fun getMagazineList(accessToken: String, page: Int): ApiResponse<MagazineResponse>

    suspend fun getMagazineDetail(accessToken: String, id:Int): ApiResponse<MagazineDetailResponse>

    suspend fun getHomeMagazine(accessToken: String): ApiResponse<MagazineResponse>
}