package com.example.data.mapper.mypage

import com.example.data.model.mypage.MyRecipeItemDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.mypage.MypageMyRecipeItem

class MypageMyRecipeMapper:Mapper<List<MyRecipeItemDto>, List<MypageMyRecipeItem>> {
    override fun mapFromEntity(type: List<MyRecipeItemDto>): List<MypageMyRecipeItem> {
        return type.map { MypageMyRecipeItem(
            foodId = it.foodId,
            name = it.name,
            veganType = it.veganType
        ) }
    }
}