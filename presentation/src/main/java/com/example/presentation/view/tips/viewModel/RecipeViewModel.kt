package com.example.presentation.view.tips.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.tips.TipsRecipeUseCase
import com.example.presentation.auth.User
import com.example.presentation.network.NetworkResult
import com.example.presentation.view.tips.viewModel.state.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeUseCase: TipsRecipeUseCase
) : ViewModel(){

    private val _recipeList = MutableStateFlow<NetworkResult<RecipeListState>>(NetworkResult.Loading())
    val recipeList: StateFlow<NetworkResult<RecipeListState>> = _recipeList

    init {
        getRecipeList()
    }

    private fun getRecipeList(){
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
}