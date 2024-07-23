package com.example.data.repository.local.search

import com.example.data.model.map.HistorySearchEntity
import com.example.domain.model.map.HistorySearch
import kotlinx.coroutines.flow.Flow

interface HistorySearchLocalDataSource{
    suspend fun insertHistorySearch(historySearchEntity: HistorySearchEntity)
    suspend fun deleteHistorySearch(historySearchEntity: HistorySearchEntity)
    suspend fun getHistorySearch(): Flow<List<HistorySearchEntity>>
    suspend fun deleteAllHistorySearch()
}