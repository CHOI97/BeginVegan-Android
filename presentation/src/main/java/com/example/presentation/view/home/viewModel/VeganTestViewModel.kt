package com.example.presentation.view.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VeganTestViewModel@Inject constructor(): ViewModel() {

    private val _userVeganType = MutableLiveData<String>()
    val userVeganType: LiveData<String> = _userVeganType

    fun setUserVeganType(veganType:String){
        _userVeganType.value = veganType
    }
}