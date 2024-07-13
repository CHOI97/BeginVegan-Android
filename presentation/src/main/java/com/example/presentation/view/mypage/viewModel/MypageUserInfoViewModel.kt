package com.example.presentation.view.mypage.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.mypage.MypageUserInfo
import com.example.domain.useCase.mypage.MypageUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MypageUserInfoViewModel @Inject constructor(
    private val mypageUserInfoUseCase: MypageUserInfoUseCase
):ViewModel(){

    private val _userInfo = MutableLiveData<MypageUserInfo>()
    val userInfo:LiveData<MypageUserInfo> = _userInfo

    init {
        getUserInfo()
    }
    private fun getUserInfo(){
        viewModelScope.launch {
            mypageUserInfoUseCase.invoke().onSuccess {
                _userInfo.value = it
            }.onFailure {
                Timber.e(it.message)
            }
        }
    }
}