package com.example.domain.useCase.map.search

import com.example.domain.model.map.HistorySearch
import com.example.domain.repository.map.HistorySearchRepository
import javax.inject.Inject

class DeleteAllHistorySearchUseCase @Inject constructor(private val historySearchRepository: HistorySearchRepository) {
    suspend operator fun invoke() =
        historySearchRepository.deleteAllHistorySearch()
}