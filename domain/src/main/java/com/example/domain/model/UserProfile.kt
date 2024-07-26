package com.example.domain.model

data class UserProfile(
    val nickName: String,
    val userLevel: UserLevels,
    val veganType: VeganTypes
)