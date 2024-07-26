package com.example.presentation.view.tips.viewModel

import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.tips.CheckChange
import com.example.domain.model.tips.RecipeDetailPosition
import com.example.domain.model.tips.TipsRecipeDetail
import com.example.domain.model.tips.TipsRecipeListItem
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
    //page 기준으로 서버에서 호출
    private val _recipeListState =
        MutableStateFlow<NetworkResult<RecipeListState>>(NetworkResult.Loading())
    val recipeListState: StateFlow<NetworkResult<RecipeListState>> = _recipeListState
    fun resetRecipeList(){
        _recipeListState.value = NetworkResult.Success(
            RecipeListState(response = mutableListOf(), isLoading = false)
        )
    }
    fun addRecipeList(newList: MutableList<TipsRecipeListItem>){
        var oldList = _recipeListState.value.data?.response
        if(oldList==null) oldList = newList
        else oldList.addAll(newList)
        _recipeListState.value = NetworkResult.Success(
            RecipeListState(response = oldList, isLoading = false)
        )
    }

    //데이터 변경시 사용하는 리스트
    private val _newRecipeList = MutableStateFlow<MutableList<TipsRecipeListItem>?>(null)
    val newRecipeList: StateFlow<MutableList<TipsRecipeListItem>?> = _newRecipeList
    fun setNewRecipeList(list:MutableList<TipsRecipeListItem>){
        _newRecipeList.value = list
    }

    private val _isContinueGetList = MutableLiveData(true)
    val isContinueGetList: LiveData<Boolean> = _isContinueGetList
    fun reSetIsContinueGetList(){
        _isContinueGetList.value = true
    }

    fun getRecipeList(page: Int) {
        viewModelScope.launch {
            recipeUseCase.getRecipeList(page).collectLatest {
                it.onSuccess { result ->
                    if (result.isEmpty()) {
                        _isContinueGetList.value = false
                    } else {
                        addRecipeList(result.toMutableList())
                    }
                }.onFailure {
                    _recipeListState.value = NetworkResult.Error("getRecipeList Failed")
                }
            }
        }
    }

    //Recipe Detail
    private val _recipeDetailData = MutableLiveData<TipsRecipeDetail>()
    val recipeDetailData: LiveData<TipsRecipeDetail> = _recipeDetailData

    private val _recipeDetailPosition = MutableStateFlow<RecipeDetailPosition?>(null)
    val recipeDetailPosition: StateFlow<RecipeDetailPosition?> = _recipeDetailPosition
    fun setRecipeDetailPosition(recipeDetailPosition: RecipeDetailPosition){
        val currentList = _recipeListState.value.data?.response!!
        currentList[recipeDetailPosition.position] = recipeDetailPosition.item
//        addRecipeList(currentList)
//        setRecipeList(currentList)
        _newRecipeList.value = currentList
        Timber.d("setRecipeDetailPosition in viewModel: position:${recipeDetailPosition.position}")
        _recipeDetailPosition.value = recipeDetailPosition
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
    fun setRecipeDetail(recipeDetail: TipsRecipeDetail){
        _recipeDetailData.value = recipeDetail
    }

    private val _nowFragment = MutableLiveData<String>()
    val nowFragment:LiveData<String> = _nowFragment
    fun setNowFragment(fragment:String){
        _nowFragment.value = fragment
    }

    private val _checkChange = MutableStateFlow(CheckChange(false, 0))
    val checkChange:StateFlow<CheckChange> = _checkChange
    fun setCheckChange(change:CheckChange){
        val a = _recipeListState.value.data?.response?.get(change.position)
        Timber.d("_recipeListState.value.data?.response?.get(change.position): ${a?.isBookmarked}")
        _checkChange.value = change
    }

    //나를 위한 레시피
    fun getRecipeForMe(page: Int){
        viewModelScope.launch {
            recipeUseCase.getRecipeMy(page).collectLatest {
                it.onSuccess { result ->
                    if (result.isEmpty()) {
                        _isContinueGetList.value = false
                    } else {
                        addRecipeList(result.toMutableList())
                    }
                }.onFailure {
                    _recipeListState.value = NetworkResult.Error("getRecipeList Failed")
                }
            }
        }
    }

    //From VeganTest
    private val _isFromTest = MutableLiveData(false)
    val isFromTest: LiveData<Boolean> = _isFromTest
    fun setIsFromTest(fromTest:Boolean){
        _isFromTest.value = fromTest
    }
}