package com.example.data.repository.local.search

import com.example.data.mapper.map.HistorySearchMapper
import com.example.data.model.map.HistorySearchEntity
import com.example.domain.model.map.HistorySearch
import com.example.domain.repository.map.HistorySearchRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class HistorySearchRepositoryImpl @Inject constructor(
    private val historySearchDataSource: HistorySearchLocalDataSource,
    private val historySearchMapper: HistorySearchMapper
) : HistorySearchRepository {
    override suspend fun insertHistorySearch(description: String) {
        try {
            val historySearchEntity = HistorySearchEntity(description = description)
            historySearchDataSource.insertHistorySearch(historySearchEntity)
            Timber.d("Success Insert HistorySearch")
        } catch (e: Exception) {
            Timber.d(e, "Insert HistorySearch Exception")
        }
    }

    override suspend fun deleteHistorySearch(historySearch: HistorySearch) {
        try {
            val historySearchEntity = historySearchMapper.mapFromEntity(historySearch)
            historySearchDataSource.deleteHistorySearch(historySearchEntity)
            Timber.d("Success Delete HistorySearch")
        } catch (e: Exception) {
            Timber.d(e, "Delete HistorySearch Exception")
        }
    }


    override suspend fun getHistorySearch(): Flow<List<HistorySearch>> {
        return historySearchDataSource.getHistorySearch().map { entityList ->
            historySearchMapper.mapToSearchList(entityList)
        }
    }

    override suspend fun deleteAllHistorySearch() {
        historySearchDataSource.deleteAllHistorySearch()
    }

}