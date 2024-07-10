package com.example.presentation.view.tips.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.tips.TipsMagazineDetail
import com.example.domain.model.tips.TipsMagazineItem
import com.example.domain.useCase.bookmarks.BookmarkUseCase
import com.example.domain.useCase.tips.TipsMagazineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MagazineViewModel @Inject constructor(
    private val tipsMagazineUseCase: TipsMagazineUseCase,
    private val bookmarkUseCase: BookmarkUseCase
) : ViewModel() {
    private val _selectedMagazineId = MutableLiveData<Int>()
    val selectedMagazineId: LiveData<Int> = _selectedMagazineId
    fun setSelectedMagazineId(magazineId: Int) {
        _selectedMagazineId.value = magazineId
    }

    private val _magazineList = MutableLiveData<List<TipsMagazineItem>>()
    val magazineList: LiveData<List<TipsMagazineItem>> = _magazineList

    private val _magazineDetail = MutableLiveData<TipsMagazineDetail>()
    val magazineDetail: LiveData<TipsMagazineDetail> = _magazineDetail

//    private val _isBookmarkSuccess = MutableLiveData<Boolean>()
//    val isBookmarkSuccess: LiveData<Boolean> = _isBookmarkSuccess
//
//    private val _isBookmarkDeleted = MutableLiveData<Boolean>()
//    val isBookmarkDeleted: LiveData<Boolean> = _isBookmarkDeleted

    init {
        getMagazineList()
    }

    private fun getMagazineList() {
        viewModelScope.launch {
            tipsMagazineUseCase.getMagazineList(0).onSuccess {
                _magazineList.value = it
            }.onFailure {
                Timber.e(it.message)
                _magazineList.value = emptyList()
            }
        }
    }

    fun getMagazineDetail() {
        viewModelScope.launch {
            tipsMagazineUseCase.getMagazineDetail(selectedMagazineId.value!!).onSuccess {
                _magazineDetail.value = it
            }.onFailure {
                Timber.e(it.message)
            }
        }
    }

    fun postBookmark(contentId: Int, contentType: String) {
        viewModelScope.launch {
            bookmarkUseCase.postBookmark(contentId, contentType).onSuccess {
                Timber.d("postBookmark onSuccess")
            }.onFailure {
                Timber.e("postBookmark onFailure")
            }
        }
    }

    fun deleteBookmark(contentId: Int, contentType: String) {
        viewModelScope.launch {
            bookmarkUseCase.deleteBookmark(contentId, contentType).onSuccess {
                Timber.d("deleteBookmark onSuccess")
            }.onFailure {
                Timber.e("deleteBookmark onFailure")
            }
        }
    }
}