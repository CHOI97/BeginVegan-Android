package com.example.domain.repository.mypage

import com.example.domain.model.mypage.MypageUserInfo

interface MypageUserInfoRepository {
    suspend fun getMypageUserInfo(): Result<MypageUserInfo>
}