package com.example.presentation.view.map.viewModel

import androidx.lifecycle.ViewModel
import com.example.domain.model.map.HistorySearch
import com.example.domain.model.map.VeganMapRestaurant
import com.example.domain.useCase.map.restaurant.GetNearRestaurantMapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class VeganMapViewModel @Inject constructor(
    private val getNearRestaurantMapUseCase: GetNearRestaurantMapUseCase
) : ViewModel() {
    private val _restaurantList = MutableStateFlow<MutableList<VeganMapRestaurant>>(mutableListOf())
    val restaurantList: StateFlow<MutableList<VeganMapRestaurant>> get() = _restaurantList

}