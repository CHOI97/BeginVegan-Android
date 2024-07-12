package com.example.domain.model.mypage

data class MypageMyMagazineItem(
    val magazineId: Int,
    val title: String,
    val thumbnail: String,
    val editor: String,
    val writeTime: String
)

data class MypageMyRecipeItem(
    val foodId:Int,
    val name:String,
    val veganType:String
)