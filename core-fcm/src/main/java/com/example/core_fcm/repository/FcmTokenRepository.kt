package com.example.core_fcm.repository

interface FcmTokenRepository {
    suspend fun saveToken(token: String)
}