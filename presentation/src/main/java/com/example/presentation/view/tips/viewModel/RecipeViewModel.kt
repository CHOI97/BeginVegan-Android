package com.example.presentation.view.tips.viewModel

import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TipsRecipeDetail
import com.example.domain.model.TipsRecipeListItem
import com.example.domain.useCase.tips.TipsRecipeUseCase
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
) : ViewModel() {

    //RecyclerView List
    private val _recipeListState =
        MutableStateFlow<NetworkResult<RecipeListState>>(NetworkResult.Loading())
    val recipeListState: StateFlow<NetworkResult<RecipeListState>> = _recipeListState

    fun addRecipeList(list: MutableList<TipsRecipeListItem>){
        _recipeListState.value = NetworkResult.Success(
            RecipeListState(response = list, isLoading = false)
        )
    }

    private val _isContinueGetList = MutableLiveData(true)
    val isContinueGetList: LiveData<Boolean> = _isContinueGetList

    //Recipe Detail
    private val _recipeDetailData = MutableLiveData<TipsRecipeDetail>()
    val recipeDetailData: LiveData<TipsRecipeDetail> = _recipeDetailData

    private val _selectedTb = MutableLiveData<CompoundButton>()
    val selectedTb: LiveData<CompoundButton> = _selectedTb
    fun setSelectedTb(view: CompoundButton) {
        _selectedTb.value = view
    }

    fun setSelectedTbIsChecked(isChecked: Boolean) {
        selectedTb.value!!.isChecked = isChecked
    }

    fun getRecipeList(page: Int) {
        viewModelScope.launch {
            recipeUseCase.getRecipeList(page).collectLatest {
                it.onSuccess { result ->
                    if (result.isEmpty()) {
                        _isContinueGetList.value = false
                    } else {
                        if (recipeListState.value.data != null) {
                            val list = recipeListState.value.data!!.response
                            list?.let { list ->
                                list.addAll(result)
                                addRecipeList(list)
                            }
                        } else {
                            addRecipeList(result.toMutableList())
                        }
                    }
                }.onFailure {
                    _recipeListState.value = NetworkResult.Error("getRecipeList Failed")
                }
            }
        }
    }

    fun getRecipeDetail(recipeId: Int) {
        viewModelScope.launch {
            recipeUseCase.getRecipeDetail(recipeId).onSuccess {
                _recipeDetailData.value = it
            }.onFailure {
                Timber.e(it.message)
            }
        }
    }

    fun getRecipeForMe(page: Int){
        viewModelScope.launch {
            recipeUseCase.getRecipeMy(page).collectLatest {
                it.onSuccess { result ->
                    if (result.isEmpty()) {
                        _isContinueGetList.value = false
                    } else {
                        if (recipeListState.value.data != null) {
                            val list = recipeListState.value.data!!.response
                            list?.let { list ->
                                list.addAll(result)
                                addRecipeList(list)
                            }
                        } else {
                            addRecipeList(result.toMutableList())
                        }
                    }
                }.onFailure {
                    _recipeListState.value = NetworkResult.Error("getRecipeList Failed")
                }
            }
        }
    }
}