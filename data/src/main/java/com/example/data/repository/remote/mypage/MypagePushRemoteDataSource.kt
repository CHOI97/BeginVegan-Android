package com.example.data.repository.remote.mypage

import com.example.data.model.mypage.MypagePushResponse
import com.skydoves.sandwich.ApiResponse

interface MypagePushRemoteDataSource {
    suspend fun patchPush(): ApiResponse<MypagePushResponse>
}