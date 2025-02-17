package com.example.presentation.view.mypage.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.mypage.MypageMyRestaurantItem
import com.example.domain.useCase.mypage.MypageMyScrapUseCase
import com.example.presentation.network.NetworkResult
import com.example.presentation.view.mypage.viewModel.state.MyRestaurantState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRestaurantViewModel @Inject constructor(
    private val myScrapUseCase: MypageMyScrapUseCase
) :ViewModel(){
    private val _myRestaurantState = MutableStateFlow<NetworkResult<MyRestaurantState>>(NetworkResult.Loading())
    val myRestaurantState: StateFlow<NetworkResult<MyRestaurantState>> = _myRestaurantState
    fun setMyRestaurantList(list:MutableList<MypageMyRestaurantItem>){
        _myRestaurantState.value = NetworkResult.Success(
            MyRestaurantState(list, false)
        )
    }

    private val _isContinueGetList = MutableLiveData(true)
    val isContinueGetList: LiveData<Boolean> = _isContinueGetList

    private val _isRestaurantEmpty = MutableLiveData(false)
    val isRestaurantEmpty: LiveData<Boolean> = _isRestaurantEmpty

    fun reSetVieModel(){
        _isContinueGetList.value = true
        setMyRestaurantList(mutableListOf())
        _isRestaurantEmpty.value = false
    }

    fun getMyRestaurant(page:Int){
        viewModelScope.launch {
            myScrapUseCase.getMyRestaurantList(page).collectLatest {
                it.onSuccess {list->
                    if(list.isEmpty()) {
                        if(page==0) _isRestaurantEmpty.value = true
                        _isContinueGetList.value = false
                    }
                    else setMyRestaurantList(list.toMutableList())
                }.onFailure {
                    _myRestaurantState.value = NetworkResult.Error("getMyRestaurantList Failure")
                }
            }
        }
    }
}