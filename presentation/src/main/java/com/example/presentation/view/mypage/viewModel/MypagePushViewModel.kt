package com.example.presentation.view.mypage.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.mypage.MypagePushUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MypagePushViewModel @Inject constructor(
    private val mypagePushUseCase: MypagePushUseCase
):ViewModel() {
    //patchPush
    fun patchPush(){
        viewModelScope.launch {
            mypagePushUseCase.patchPush().onSuccess {
                Timber.d("patchPush onSuccess")
            }.onFailure {
                Timber.e("patchPush onFailure")
            }
        }
    }
}