package com.example.domain.model.mypage

data class MypageUserInfo(
    val id: Int,
    val imageUrl:String,
    val nickname:String,
    val userLevel:String,
    val veganType:String,
    val point:Int
)
