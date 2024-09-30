package com.example.presentation.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_fcm.useCase.FcmTokenUseCase
import com.example.domain.useCase.userInfo.HomeUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeUserInfoUseCase: HomeUserInfoUseCase,
    private val fcmTokenUseCase: FcmTokenUseCase
) : ViewModel() {

    private val _nickName = MutableStateFlow<String>("")
    val nickName: StateFlow<String> get() = _nickName.asStateFlow()

    private val _tipsMoveToRecipe = MutableLiveData(false)
    val tipsMoveToRecipe: LiveData<Boolean> = _tipsMoveToRecipe
    fun setTipsMoveToRecipe(isMove: Boolean) {
        _tipsMoveToRecipe.value = isMove
    }

    private val _fromTest = MutableLiveData(false)
    val fromTest: LiveData<Boolean> = _fromTest
    fun setFromTest(isMove: Boolean) {
        _fromTest.value = isMove
    }

    private val _fromMyMagazine = MutableLiveData(false)
    val fromMyMagazine: LiveData<Boolean> = _fromMyMagazine
    fun setFromMyMagazine(isMove:Boolean){
        _fromMyMagazine.value = isMove
    }

    private val _mapMoveToReview = MutableLiveData(false)
    val mapMoveToReview: LiveData<Boolean> = _mapMoveToReview
    fun setMapMoveToReview(isMove: Boolean) {
        _mapMoveToReview.value = isMove
    }
    fun getUserData(){
        viewModelScope.launch {

        }
    }
    fun getUserInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            homeUserInfoUseCase.invoke().onSuccess {

            }.onFailure {

            }
        }

    }

    fun postFcmPush(){
        viewModelScope.launch(Dispatchers.IO) {
            fcmTokenUseCase.postFcmMessage(
                "가나다라마바사","테스트 메시지 입니다.","MYPAGE",1,"REVIEW_RECOMMEND",userLevel = null
            )
        }
    }
}