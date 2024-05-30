package com.example.domain.repository.auth

import com.example.domain.model.AuthToken

/** 회원 로그인, 회원가입, 추가정보 입력 Repository
 *  구현 Impl -> Data Module */
interface AuthRepository {

    suspend fun signUp(email: String, providerId: String): Boolean

    suspend fun signIn(email: String, providerId: String): AuthToken

    // Multipart
//    suspend fun signUpDetail(nick: String, veganType: String, )
}