package com.example.domain.repository.userInfo

import com.example.domain.model.core.BasicResult

interface SaveUserInfoRepository {
    suspend fun updateUserInfo(
        nickName: String,
        veganType: String,
        isDefaultImage: Boolean,
        imageUri: String?
    ): Result<BasicResult>
}