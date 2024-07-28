package com.example.domain.useCase.mypage

import com.example.domain.repository.mypage.MypagePushRepository
import javax.inject.Inject

class MypagePushUseCase @Inject constructor(
    private val mypagePushRepository: MypagePushRepository
){
    suspend fun patchPush(): Result<Boolean> = mypagePushRepository.patchPush()
}