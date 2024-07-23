package com.example.domain.useCase.map.search

import com.example.domain.model.map.HistorySearch
import com.example.domain.repository.map.HistorySearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertHistorySearchUseCase @Inject constructor(private val historySearchRepository: HistorySearchRepository) {
    suspend operator fun invoke(description: String) = historySearchRepository.insertHistorySearch(description)
}