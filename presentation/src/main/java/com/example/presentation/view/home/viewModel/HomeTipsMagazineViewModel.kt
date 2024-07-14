package com.example.presentation.view.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.tips.TipsMagazineItem
import com.example.domain.useCase.tips.TipsMagazineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeTipsMagazineViewModel @Inject constructor(
    private val magazineUseCase: TipsMagazineUseCase
):ViewModel() {

    private val _homeMagazineList = MutableLiveData<List<TipsMagazineItem>>()
    val homeMagazineList:LiveData<List<TipsMagazineItem>> = _homeMagazineList

    fun getHomeMagazineList(){
        viewModelScope.launch {
            magazineUseCase.getHomeMagazine().onSuccess {
                _homeMagazineList.value = it
            }.onFailure {
                Timber.e("getHomeMagazine onFailure")
            }
        }
    }
}