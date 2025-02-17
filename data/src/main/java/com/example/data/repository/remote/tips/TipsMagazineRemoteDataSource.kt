package com.example.data.repository.remote.tips

import com.example.data.model.tips.MagazineDetailResponse
import com.example.data.model.tips.MagazineResponse
import com.skydoves.sandwich.ApiResponse

interface TipsMagazineRemoteDataSource {
    suspend fun getMagazineList(page: Int): ApiResponse<MagazineResponse>

    suspend fun getMagazineDetail(id:Int): ApiResponse<MagazineDetailResponse>

    suspend fun getHomeMagazine(): ApiResponse<MagazineResponse>
}