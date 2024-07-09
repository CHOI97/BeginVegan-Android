package com.example.data.mapper.tips

import com.example.data.model.tips.TipsRecipeListItemDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.TipsRecipeListItem

class TipsRecipeMapper: Mapper<TipsRecipeListItemDto, TipsRecipeListItem> {
    override fun mapFromEntity(type: TipsRecipeListItemDto): TipsRecipeListItem = TipsRecipeListItem(
        id = type.id,
        name = type.name,
        veganType = type.veganType,
        isBookmarked = type.isBookmarked
    )

    fun mapToRecipeList(dtoList: List<TipsRecipeListItemDto>): List<TipsRecipeListItem>{
        return dtoList.map { mapFromEntity(it) }
    }
}