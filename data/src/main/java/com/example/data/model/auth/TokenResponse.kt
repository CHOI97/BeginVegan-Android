package com.example.data.model.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String
)