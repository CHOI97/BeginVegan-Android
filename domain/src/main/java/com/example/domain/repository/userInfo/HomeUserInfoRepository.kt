package com.example.domain.repository.userInfo

import com.example.domain.model.userInfo.HomeUserInfo

interface HomeUserInfoRepository {
    suspend fun getHomeUserInfo(): Result<HomeUserInfo>
}