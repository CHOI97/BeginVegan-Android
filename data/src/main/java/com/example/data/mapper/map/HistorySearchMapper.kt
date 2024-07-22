package com.example.data.mapper.map

import com.example.data.model.map.HistorySearchEntity
import com.example.data.model.tips.TipsRecipeListItemDto
import com.example.domain.mapper.DBMapper
import com.example.domain.model.TipsRecipeListItem
import com.example.domain.model.map.HistorySearch

class HistorySearchMapper: DBMapper<HistorySearch, HistorySearchEntity> {
    override fun mapFromEntity(type: HistorySearch): HistorySearchEntity {
        return HistorySearchEntity(
            description = type.description
        )
    }

    override fun entityFromMap(type: HistorySearchEntity): HistorySearch {
        return HistorySearch(
            id = type.id,
            description =  type.description
        )
    }

    fun mapToSearchList(searchList: List<HistorySearchEntity>): List<HistorySearch>{
        return searchList.map { entityFromMap(it) }
    }
}