package com.example.data.mapper.userInfo

import com.example.data.model.userInfo.HomeUserInfoDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.userInfo.HomeUserInfo

class HomeUserInfoMapper: Mapper<HomeUserInfoDto, HomeUserInfo> {
    override fun mapFromEntity(type: HomeUserInfoDto): HomeUserInfo = HomeUserInfo(
        nickName = type.nickname,
        userLevel = type.userLevel
    )

}