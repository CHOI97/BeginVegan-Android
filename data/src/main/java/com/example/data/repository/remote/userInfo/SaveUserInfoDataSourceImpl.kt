package com.example.data.repository.remote.userInfo

import com.example.data.model.auth.AuthRequest
import com.example.data.model.auth.SignInResponse
import com.example.data.model.core.BaseResponse
import com.example.data.model.userInfo.AddUserInfoRequest
import com.example.data.retrofit.UserInfoService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class SaveUserInfoDataSourceImpl @Inject constructor(
    private val userInfoService: UserInfoService
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
        return userInfoService.updateUserInfo(
            addUserInfoRequest.addUserInfoReq,
            addUserInfoRequest.isDefaultImage,
            multiPartBody
        )
    }

}