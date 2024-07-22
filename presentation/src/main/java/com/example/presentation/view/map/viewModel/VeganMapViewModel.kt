package com.example.presentation.view.map.viewModel

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
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VeganMapViewModel @Inject constructor() : ViewModel() {

    private val _searchList = MutableStateFlow<List<HistorySearch>>(emptyList())
    val searchList: StateFlow<List<HistorySearch>> get() = _searchList


}