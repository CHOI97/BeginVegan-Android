package com.example.presentation.view.mypage.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.mypage.MypageMyMagazineItem
import com.example.domain.model.mypage.MypageMyRecipeItem
import com.example.domain.useCase.mypage.MypageMyScrapUseCase
import com.example.presentation.network.NetworkResult
import com.example.presentation.view.mypage.viewModel.state.MyMagazineState
import com.example.presentation.view.mypage.viewModel.state.MyRecipeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecipeViewModel @Inject constructor(
    private val myScrapUseCase: MypageMyScrapUseCase
):ViewModel(){
    private val _myRecipesState = MutableStateFlow<NetworkResult<MyRecipeState>>(NetworkResult.Loading())
    val myRecipesState: StateFlow<NetworkResult<MyRecipeState>> = _myRecipesState
    fun setMyRecipeList(list:MutableList<MypageMyRecipeItem>){
        _myRecipesState.value = NetworkResult.Success(
            MyRecipeState(list, false)
        )
    }

    private val _isContinueGetList = MutableLiveData(true)
    val isContinueGetList: LiveData<Boolean> = _isContinueGetList
    fun reSetVieModel(){
    }

    private val _isRecipeEmpty = MutableLiveData(false)
    val isRecipeEmpty: LiveData<Boolean> = _isRecipeEmpty

    fun getMyRecipe(page:Int){
        viewModelScope.launch {
            myScrapUseCase.getMyRecipeList(page).collectLatest {
                it.onSuccess {list->
                    if(list.isEmpty()) {
                        if(page==0) _isRecipeEmpty.value = true
                        _isContinueGetList.value = false
                    }
                    else setMyRecipeList(list.toMutableList())
                }.onFailure {
                    _myRecipesState.value = NetworkResult.Error("getMyMagazineList Failure")
                }
            }
        }
    }
}