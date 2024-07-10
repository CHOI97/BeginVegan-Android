package com.example.data.repository.remote.userInfo

import com.example.data.model.auth.SignInResponse
import com.example.data.model.core.BaseResponse
import com.example.data.model.userInfo.AddUserInfoRequest
import com.skydoves.sandwich.ApiResponse
import java.io.File

interface SaveUserInfoDataSource {
    suspend fun updateUserInfo(
        addUserInfoRequest: AddUserInfoRequest,
        imageUri: String?
    ): ApiResponse<BaseResponse>
}