package com.example.domain.useCase.userInfo

import com.example.domain.model.mypage.MypageUserInfo
import com.example.domain.model.userInfo.HomeUserInfo
import com.example.domain.repository.mypage.MypageUserInfoRepository
import com.example.domain.repository.userInfo.HomeUserInfoRepository
import javax.inject.Inject

class HomeUserInfoUserCase @Inject constructor(
    private val homeUserInfoRepository: HomeUserInfoRepository
){
    suspend operator fun invoke():Result<HomeUserInfo> {
        return homeUserInfoRepository.getHomeUserInfo()
    }
}