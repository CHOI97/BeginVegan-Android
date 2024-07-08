package com.example.domain.useCase.userInfo

import com.example.domain.model.core.OperationResult
import com.example.domain.repository.userInfo.SaveUserInfoRepository
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(private val saveUserInfoRepository: SaveUserInfoRepository) {
    suspend operator fun invoke(
        nickName: String,
        veganType: String,
        isDefaultImage: Boolean,
        imageUri: String?
    ): Result<OperationResult> =
        saveUserInfoRepository.updateUserInfo(nickName, veganType, isDefaultImage, imageUri)
}