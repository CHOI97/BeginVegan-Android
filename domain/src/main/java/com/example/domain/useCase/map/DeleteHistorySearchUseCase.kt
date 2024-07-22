package com.example.domain.useCase.map

import com.example.domain.model.map.HistorySearch
import com.example.domain.repository.map.HistorySearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteHistorySearchUseCase @Inject constructor(private val historySearchRepository: HistorySearchRepository) {
    suspend operator fun invoke(historySearch: HistorySearch) =
        historySearchRepository.deleteHistorySearch(historySearch)
}