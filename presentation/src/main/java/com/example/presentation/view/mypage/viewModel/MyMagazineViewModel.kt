package com.example.presentation.view.mypage.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.mypage.MypageMyMagazineItem
import com.example.domain.useCase.mypage.MypageMyScrapUseCase
import com.example.presentation.network.NetworkResult
import com.example.presentation.view.mypage.viewModel.state.MyMagazineState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyMagazineViewModel @Inject constructor(
    private val myScrapUseCase: MypageMyScrapUseCase
):ViewModel() {

    private val _myMagazinesState = MutableStateFlow<NetworkResult<MyMagazineState>>(NetworkResult.Loading())
    val myMagazineState: StateFlow<NetworkResult<MyMagazineState>> = _myMagazinesState
    fun setMyMagazineList(list:MutableList<MypageMyMagazineItem>){
        Timber.d("inViewModel list: $list")
        _myMagazinesState.value = NetworkResult.Success(
            MyMagazineState(list, false)
        )
    }

    private val _isContinueGetList = MutableLiveData(true)
    val isContinueGetList: LiveData<Boolean> = _isContinueGetList
    fun reSetVieModel(){
        _isContinueGetList.value = true
        setMyMagazineList(mutableListOf())
        _isMagazineEmpty.value = false
    }

    private val _isMagazineEmpty = MutableLiveData(false)
    val isMagazineEmpty:LiveData<Boolean> = _isMagazineEmpty

    fun getMyMagazine(page:Int){
        viewModelScope.launch {
            myScrapUseCase.getMyMagazineList(page).collectLatest {
                it.onSuccess {list->
                    if(list.isEmpty()) {
                        if(page==0) _isMagazineEmpty.value = true
                        else _isContinueGetList.value = false
                    }
                    else setMyMagazineList(list.toMutableList())
                }.onFailure {
                    _myMagazinesState.value = NetworkResult.Error("getMyMagazineList Failure")
                }
            }
        }

    }
}