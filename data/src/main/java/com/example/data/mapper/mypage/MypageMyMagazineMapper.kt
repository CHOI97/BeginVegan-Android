package com.example.data.mapper.mypage

import com.example.data.model.mypage.MyMagazineItemDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.mypage.MypageMyMagazineItem

class MypageMyMagazineMapper:Mapper<List<MyMagazineItemDto>, List<MypageMyMagazineItem>> {
    override fun mapFromEntity(type: List<MyMagazineItemDto>): List<MypageMyMagazineItem> {
        return type.map { MypageMyMagazineItem(
            magazineId = it.magazineId,
            title = it.title,
            writeTime = it.writeTime,
            thumbnail = it.thumbnail,
            editor = it.editor
        ) }
    }
}