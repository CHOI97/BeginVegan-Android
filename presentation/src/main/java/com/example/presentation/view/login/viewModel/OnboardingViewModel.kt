package com.example.presentation.view.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OnboardingViewModel @Inject constructor() : ViewModel() {

    var nickName = MutableLiveData<String>()
    var veganLevel = MutableLiveData<String>()

    private var _validNickName = MutableLiveData<Boolean>()
    private var _validVeganLevel = MutableLiveData<Boolean>()

    val validNickName: LiveData<Boolean> = _validNickName
    val validVeganLevel: LiveData<Boolean> = _validVeganLevel

    init {
        _validNickName.value = false
        _validVeganLevel.value = false
    }

    fun validateNickName(input: String): Boolean {
        // 입력이 2~12자인지 확인
        if (input.length !in 2..12) {
            return false
        }

        // 입력이 한글 또는 영문으로 이루어져 있는지 확인
        if (!input.all { it in '가'..'힣' || it in 'a'..'z' || it in 'A'..'Z' }) {
            return false
        }

        // 입력에 공백이 포함되어 있는지 확인
        if (input.contains(" ")) {
            return false
        }

        // 모든 조건을 만족하면 true 반환
        return true
    }

    fun setValidVeganLevel(){
        _validVeganLevel.value = true
    }
    fun setValidNickName(check: Boolean){
        _validNickName.value = check
    }
    fun checkValid(): Boolean {
        return validNickName.value ?: false && validVeganLevel.value ?: false
    }
}