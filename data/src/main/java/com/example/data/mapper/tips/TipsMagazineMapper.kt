package com.example.data.mapper.tips

import com.example.data.model.tips.TipsMagazineItemDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.tips.TipsMagazineItem
import com.example.domain.model.tips.TipsMagazineList

class TipsMagazineMapper:Mapper<List<TipsMagazineItemDto>, List<TipsMagazineItem>> {
    override fun mapFromEntity(type: List<TipsMagazineItemDto>): List<TipsMagazineItem> {
        return type.map { mapItem(it) }
    }

    private fun mapItem(type: TipsMagazineItemDto): TipsMagazineItem{
        return TipsMagazineItem(
            id = type.id,
            title = type.title,
            thumbnail = type.thumbnail,
            editor = type.editor,
            createdDate = type.createdDate,
            isBookmarked = type.isBookmarked
        )
    }
}