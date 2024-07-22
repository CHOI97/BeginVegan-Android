package com.example.data.repository.remote.userInfo

import com.example.data.model.userInfo.HomeUserInfoResponse
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.retrofit.UserInfoService
import com.example.data.retrofit.VeganMapService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HomeUserInfoDataSourceImpl @Inject constructor(
    private val getHomeUserInfo: UserInfoService,
    private val authTokenDataSource: AuthTokenDataSource
): HomeUserInfoDataSource{
    override suspend fun getHomeUserInfo(): ApiResponse<HomeUserInfoResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"

        return getHomeUserInfo.getHomeUserInfo(
            authHeader
        )
    }

}