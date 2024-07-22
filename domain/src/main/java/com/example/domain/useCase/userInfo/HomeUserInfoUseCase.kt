package com.example.domain.useCase.userInfo

import com.example.domain.model.userInfo.HomeUserInfo
import com.example.domain.repository.userInfo.HomeUserInfoRepository
import javax.inject.Inject

class HomeUserInfoUseCase @Inject constructor(
    private val homeUserInfoRepository: HomeUserInfoRepository
){
    suspend operator fun invoke():Result<HomeUserInfo> {
        return homeUserInfoRepository.getHomeUserInfo()
    }
}