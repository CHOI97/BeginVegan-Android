package com.example.data.retrofit

import com.example.data.model.tips.MagazineDetailRequest
import com.example.data.model.tips.MagazineDetailResponse
import com.example.data.model.tips.MagazineResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TipsMagazineService {
    @GET("/api/v1/magazines")
    suspend fun getMagazineList(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): ApiResponse<MagazineResponse>

    @GET("/api/v1/magazines/detail")
    suspend fun getMagazineDetail(
        @Header("Authorization") token: String,
        @Query("id") id:Int
    ): ApiResponse<MagazineDetailResponse>

    @GET("/api/v1/home/magazine")
    suspend fun getHomeMagazine(
        @Header("Authorization") token: String
    ): ApiResponse<MagazineResponse>
}