package com.example.data.repository.remote.userInfo

import com.example.data.model.core.BaseResponse
import com.example.data.model.userInfo.AddUserInfoRequest
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.retrofit.UserInfoService
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.flow.first
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class SaveUserInfoDataSourceImpl @Inject constructor(
    private val userInfoService: UserInfoService,
    private val authTokenDataSource: AuthTokenDataSource
) : SaveUserInfoDataSource {

    override suspend fun updateUserInfo(
        addUserInfoRequest: AddUserInfoRequest,
        imageUri: String?
    ): ApiResponse<BaseResponse> {
        var multiPartBody: MultipartBody.Part? = null
        if (imageUri != null) {
            val file = File(imageUri)
            val requestBody = file!!.asRequestBody("file".toMediaTypeOrNull())
            multiPartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)
        }

        // `accessToken`을 가져와서 헤더로 추가
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"

        return userInfoService.updateUserInfo(
            authHeader,
            addUserInfoRequest.addUserInfoReq,
            addUserInfoRequest.isDefaultImage,
            multiPartBody
        )
    }
}
