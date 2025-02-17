package com.example.data.mapper.tips

import com.example.data.model.tips.MagazineContentDto
import com.example.data.model.tips.TipsMagazineDetailDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.tips.MagazineContent
import com.example.domain.model.tips.TipsMagazineDetail

class TipsMagazineDetailMapper:Mapper<TipsMagazineDetailDto, TipsMagazineDetail> {
    override fun mapFromEntity(type: TipsMagazineDetailDto): TipsMagazineDetail {
        return TipsMagazineDetail(
            id = type.id,
            title = type.title,
            editor = type.editor,
            source = type.source,
            thumbnail = type.thumbnail,
            isBookmarked = type.isBookmarked,
            createdDate = type.createdDate,
            magazineContents = mapItem(type.magazineContents)
        )
    }

    private fun mapItem(type: List<MagazineContentDto>): List<MagazineContent>{
        return type.map { MagazineContent(
            content = it.content,
            sequence = it.sequence,
            isBold = it.isBold
        ) }
    }
}