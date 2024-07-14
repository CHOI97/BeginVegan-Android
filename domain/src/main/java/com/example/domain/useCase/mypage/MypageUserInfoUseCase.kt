package com.example.domain.useCase.mypage

import com.example.domain.model.mypage.MypageUserInfo
import com.example.domain.repository.mypage.MypageUserInfoRepository
import javax.inject.Inject

class MypageUserInfoUseCase @Inject constructor(
    private val mypageUserInfoRepository: MypageUserInfoRepository
){
    suspend operator fun invoke():Result<MypageUserInfo> {
        return mypageUserInfoRepository.getMypageUserInfo()
    }
}