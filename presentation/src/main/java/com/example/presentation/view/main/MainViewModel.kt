package com.example.presentation.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    private val _tipsMoveToRecipe = MutableLiveData(false)
    val tipsMoveToRecipe: LiveData<Boolean> = _tipsMoveToRecipe
    fun setTipsMoveToRecipe(isMove:Boolean){
        _tipsMoveToRecipe.value = isMove
    }

    private val _fromTest = MutableLiveData(false)
    val fromTest: LiveData<Boolean> = _fromTest
    fun setFromTest(isMove:Boolean){
        _fromTest.value = isMove
    }

    private val _fromMyMagazine = MutableLiveData(false)
    val fromMyMagazine: LiveData<Boolean> = _fromMyMagazine
    fun setFromMyMagazine(isMove:Boolean){
        _fromMyMagazine.value = isMove
    }

    private val _mapMoveToReview = MutableLiveData(false)
    val mapMoveToReview:LiveData<Boolean> = _mapMoveToReview
    fun setMapMoveToReview(isMove:Boolean){
        _mapMoveToReview.value = isMove
    }
}