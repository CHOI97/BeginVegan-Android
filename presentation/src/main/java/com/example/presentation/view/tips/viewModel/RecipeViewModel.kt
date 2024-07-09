package com.example.presentation.view.tips.viewModel

import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TipsRecipeDetail
import com.example.domain.useCase.tips.TipsRecipeUseCase
import com.example.presentation.auth.User
import com.example.presentation.network.NetworkResult
import com.example.presentation.view.tips.viewModel.state.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeUseCase: TipsRecipeUseCase
) : ViewModel(){

    private val _recipeList = MutableStateFlow<NetworkResult<RecipeListState>>(NetworkResult.Loading())
    val recipeList: StateFlow<NetworkResult<RecipeListState>> = _recipeList

    private val _recipeDetailData = MutableLiveData<TipsRecipeDetail>()
    val recipeDetailData : LiveData<TipsRecipeDetail> = _recipeDetailData

    private val _selectedTb = MutableLiveData<CompoundButton>()
    val selectedTb: LiveData<CompoundButton> = _selectedTb
    fun setSelectedTb(view:CompoundButton){
        _selectedTb.value = view
    }
    fun setSelectedTbIsChecked(isChecked:Boolean){
        selectedTb.value!!.isChecked = isChecked
    }

    fun getRecipeList(){
        viewModelScope.launch {
            recipeUseCase.getRecipeList(User.accessToken, 0).collectLatest{
                it.onSuccess {result ->
                    _recipeList.value = NetworkResult.Success(
                        RecipeListState(
                            response = result,
                            isLoading = false
                        ))
                }.onFailure {
                    _recipeList.value = NetworkResult.Error("getRecipeList Failed")
                }
            }
        }
    }

    fun getRecipeDetail(recipeId:Int){
        viewModelScope.launch {
            recipeUseCase.getRecipeDetail(User.accessToken, recipeId).onSuccess {
                _recipeDetailData.value = it
            }.onFailure {
                Timber.e(it.message)
            }
        }
    }
}