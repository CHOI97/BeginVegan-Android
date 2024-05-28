package com.example.domain.repository.auth

interface AuthRepository {

    suspend fun signUp(email: String, providerId: String): Result<Boolean>

    suspend fun signIn(email: String, providerId: String): Result<Boolean>

    // Multipart
//    suspend fun signUpDetail(nick: String, veganType: String, )
}