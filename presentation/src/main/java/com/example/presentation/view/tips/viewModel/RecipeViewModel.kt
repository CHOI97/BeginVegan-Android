package com.example.presentation.view.tips.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TipsRecipeListItem
import com.example.domain.useCase.tips.TipsRecipeUseCase
import com.example.presentation.auth.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeUseCase: TipsRecipeUseCase
) : ViewModel(){

    private val _recipeList = MutableLiveData<List<TipsRecipeListItem>>()
    val recipeList : LiveData<List<TipsRecipeListItem>> = _recipeList

    init {
        getRecipeList()
    }

    private fun getRecipeList(){
        viewModelScope.launch {
            recipeUseCase.getRecipeList(User.accessToken, 0).onSuccess {
                _recipeList.value = it
            }.onFailure {
                Timber.e(it.message)
            }
        }
    }
}