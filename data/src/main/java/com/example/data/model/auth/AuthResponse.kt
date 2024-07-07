package com.example.data.model.auth

import com.example.data.model.auth.TokenResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class AuthResponse(
    @Json(name= "message")
    val message: String? = null ,
    @Json(name= "authRes")
    val authRes: TokenResponse,
    @Json(name= "signUpCompleted")
    val signUpCompleted: Boolean
)