package com.example.presentation.view.home.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TipsRecipeListItem
import com.example.domain.model.tips.TipsMagazineItem
import com.example.domain.useCase.tips.TipsMagazineUseCase
import com.example.domain.useCase.tips.TipsRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeTipsViewModel @Inject constructor(
    private val magazineUseCase: TipsMagazineUseCase,
    private val recipeUseCase: TipsRecipeUseCase
):ViewModel() {

    private val _homeMagazineList = MutableLiveData<List<TipsMagazineItem>>()
    val homeMagazineList:LiveData<List<TipsMagazineItem>> = _homeMagazineList

    fun getHomeMagazineList(){
        viewModelScope.launch {
            magazineUseCase.getHomeMagazine().onSuccess {
                _homeMagazineList.value = it
            }.onFailure {
                Timber.e("getHomeMagazine onFailure")
            }
        }
    }

    private val _homeRecipeList = MutableLiveData<List<TipsRecipeListItem>>()
    val homeRecipeList:LiveData<List<TipsRecipeListItem>> = _homeRecipeList

    fun getHomeRecipeList(){
        viewModelScope.launch {
            recipeUseCase.getHomeRecipe().onSuccess {
                _homeRecipeList.value = it
            }.onFailure {
                Timber.e("getHomeRecipe onFailure")
            }
        }
    }
}