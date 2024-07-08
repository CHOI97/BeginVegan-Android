package com.example.domain.repository.userInfo

import com.example.domain.model.auth.AuthToken
import com.example.domain.model.core.OperationResult

interface SaveUserInfoRepository {
    suspend fun updateUserInfo(
        nickName: String,
        veganType: String,
        isDefaultImage: Boolean,
        imageUri: String?
    ): Result<OperationResult>
}