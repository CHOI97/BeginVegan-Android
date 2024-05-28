package com.example.presentation.view.tips.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MagazineViewModel:ViewModel() {
    private val _selectedMagazineId = MutableLiveData<Int>()
    val selectedMagazineId:LiveData<Int> = _selectedMagazineId
    fun setSelectedMagazineId(magazineId:Int){
        _selectedMagazineId.value = magazineId
    }
}