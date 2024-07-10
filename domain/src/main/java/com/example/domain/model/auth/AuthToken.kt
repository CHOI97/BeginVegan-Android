package com.example.domain.model.auth
/** User Toekn Domain Model **/
data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
    val additionalInfo: Boolean,
)
