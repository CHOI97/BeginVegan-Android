package com.example.domain.model
/** User Token Entity **/
data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
)
