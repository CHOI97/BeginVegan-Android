package com.example.data.mapper.user

import com.example.data.model.user.UserData
import com.example.domain.mapper.Mapper
import com.example.domain.model.UserProfile

class UserDataMapper : Mapper<UserData, UserProfile> {
    override fun mapFromEntity(type: UserData): UserProfile {
        return UserProfile(
            nickName = type.nickName,
            userLevel = type.userLevel,
            veganType = type.veganType
        )
    }
    fun mapToEntity(type: UserProfile): UserData {
        return UserData(
            nickName = type.nickName,
            userLevel = type.userLevel,
            veganType = type.veganType
        )
    }
}