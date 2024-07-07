package com.example.data.mapper.tips

import com.example.data.model.tips.TipsMagazineItemDto
import com.example.data.model.tips.TipsMagazineListDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.tips.TipsMagazineItem
import com.example.domain.model.tips.TipsMagazineList

class TipsMagazineMapper:Mapper<TipsMagazineListDto, TipsMagazineList> {
    override fun mapFromEntity(type: TipsMagazineListDto): TipsMagazineList {
        return TipsMagazineList(type.tipsMagazineList.map { mapItem(it) })
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