package com.example.presentation.view.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VeganTestViewModel@Inject constructor(): ViewModel() {

    private val _userVeganTypeNum = MutableLiveData<Int>()
    val userVeganTypeNum: LiveData<Int> = _userVeganTypeNum

    fun setUserVeganTypeNum(veganTypeNum:Int){
        _userVeganTypeNum.value = veganTypeNum
    }
}