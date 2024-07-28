package com.example.domain.repository.mypage


interface MypagePushRepository {
    suspend fun patchPush(): Result<Boolean>
}