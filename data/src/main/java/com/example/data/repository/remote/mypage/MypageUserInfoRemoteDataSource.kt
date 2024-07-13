package com.example.data.repository.remote.mypage

import com.example.data.model.mypage.MypageUserInfoResponse
import com.skydoves.sandwich.ApiResponse

interface MypageUserInfoRemoteDataSource {
    suspend fun getMypageUserInfo(): ApiResponse<MypageUserInfoResponse>
}