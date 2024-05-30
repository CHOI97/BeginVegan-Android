package com.example.data.model.auth

import com.example.data.model.auth.TokenResponse
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AuthResponse(
    val message: String? = null,
    val authRes: TokenResponse? = null,
    val signUpCompleted: Boolean? = null
)