package com.example.data.repository.remote.userInfo

import com.example.data.model.userInfo.HomeUserInfoResponse
import com.skydoves.sandwich.ApiResponse

interface HomeUserInfoDataSource {
    suspend fun getHomeUserInfo(): ApiResponse<HomeUserInfoResponse>
}