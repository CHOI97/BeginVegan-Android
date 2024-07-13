package com.example.data.mapper.mypage

import com.example.data.model.mypage.MypageUserInfoDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.mypage.MypageUserInfo

class MypageUserInfoMapper:Mapper<MypageUserInfoDto, MypageUserInfo> {
    override fun mapFromEntity(type: MypageUserInfoDto): MypageUserInfo {
        return MypageUserInfo(
            id = type.id,
            imageUrl = type.imageUrl,
            nickname = type.nickname,
            userLevel = type.userLevel,
            veganType = type.veganType,
            point = type.point
        )
    }
}