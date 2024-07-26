package com.example.data.model.user

import com.example.domain.model.UserLevels
import com.example.domain.model.VeganTypes

data class UserData(
    val nickName: String,
    val userLevel: UserLevels,
    val veganType: VeganTypes
)
