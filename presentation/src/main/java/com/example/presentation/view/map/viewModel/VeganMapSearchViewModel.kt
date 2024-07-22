package com.example.presentation.view.map.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.map.HistorySearch
import com.example.domain.useCase.map.DeleteHistorySearchUseCase
import com.example.domain.useCase.map.GetAllHistorySearchUseCase
import com.example.domain.useCase.map.InsertHistorySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VeganMapSearchViewModel @Inject constructor(
    private val insertHistorySearchUseCase: InsertHistorySearchUseCase,
    private val deleteHistorySearchUseCase: DeleteHistorySearchUseCase,
    private val getAllHistorySearchUseCase: GetAllHistorySearchUseCase
) : ViewModel() {

    private val _searchList = MutableStateFlow<List<HistorySearch>>(emptyList())
    val searchList: StateFlow<List<HistorySearch>> get() = _searchList

    fun fetchAllHistory() {
        viewModelScope.launch {
            getAllHistorySearchUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    Timber.d(e, "ViewModel Get HistorySearch Exception")
                    _searchList.value = emptyList()
                }
                .collect { historySearchList ->
                    Timber.d("collect search list $historySearchList")
                    _searchList.value = historySearchList
                }
        }
    }


    fun insertHistory(description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            insertHistorySearchUseCase(description)
        }
    }

    fun deleteHistory(historySearch: HistorySearch) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteHistorySearchUseCase(historySearch)
        }
    }
}