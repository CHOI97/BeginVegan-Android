package com.example.presentation.view.map.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.map.HistorySearch
import com.example.domain.model.map.VeganMapRestaurant
import com.example.domain.useCase.map.restaurant.GetNearRestaurantMapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VeganMapViewModel @Inject constructor(
    private val getNearRestaurantMapUseCase: GetNearRestaurantMapUseCase
) : ViewModel() {
    private val _restaurantList = MutableStateFlow<List<VeganMapRestaurant>>(mutableListOf())
    val restaurantList: StateFlow<List<VeganMapRestaurant>> get() = _restaurantList

    fun fetchNearRestaurantMap(page: Int, latitude: Double, longitude: Double) {
        Timber.d("fetchNearRestaurantMap")
        viewModelScope.launch {
            getNearRestaurantMapUseCase.invoke(page, latitude.toString(), longitude.toString())
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    Timber.d(e, "fetchNearRestaurantMap Exception")
                }
                .collect { veganRestaurantList ->
                    Timber.d("fetchNearRestaurantMap list $veganRestaurantList")
                    _restaurantList.value = veganRestaurantList
                }
        }
    }

}