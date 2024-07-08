package com.example.data.model.auth

import com.example.data.model.auth.AuthResponse
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignInResponse (
    val check: Boolean,
    val information: AuthResponse
)
