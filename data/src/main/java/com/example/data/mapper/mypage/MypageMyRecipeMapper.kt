package com.example.data.mapper.mypage

import com.example.data.model.mypage.MyRecipeItemDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.tips.TipsRecipeListItem

class MypageMyRecipeMapper:Mapper<List<MyRecipeItemDto>, List<TipsRecipeListItem>> {
    override fun mapFromEntity(type: List<MyRecipeItemDto>): List<TipsRecipeListItem> {
        return type.map { TipsRecipeListItem(
            id = it.foodId,
            name = it.name,
            veganType = it.veganType,
            isBookmarked = true
        ) }
    }
}