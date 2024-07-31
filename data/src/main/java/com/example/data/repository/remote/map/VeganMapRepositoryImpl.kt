package com.example.data.repository.remote.map

import com.example.data.mapper.map.VeganMapMapper
import com.example.domain.model.map.VeganMapRestaurant
import com.example.domain.repository.map.VeganMapRepository
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class VeganMapRepositoryImpl @Inject constructor(
    private val veganMapRemoteDataSource: VeganMapRemoteDataSource,
    private val veganMapMapper: VeganMapMapper
) : VeganMapRepository {
    override suspend fun getNearRestaurantWithPermission() {
    }

    override suspend fun getNearRestaurantWithOutPermission() {
    }

    override suspend fun getNearRestaurantMap(
        page: Int,
        latitude: String,
        longitude: String
    ): Flow<List<VeganMapRestaurant>> = flow {
        val response = veganMapRemoteDataSource.getNearRestaurantMap(page, latitude, longitude)
        response.suspendOnSuccess {
            Timber.d("getNearRestaurantMap successful")
            val restaurants = veganMapMapper.mapFromEntity(data.information)
            emit(restaurants)
        }.suspendOnError {
            Timber.e("Error fetching restaurants: ${this.errorBody}")
            emit(emptyList())
        }
    }


    override suspend fun getRestaurantDetail() {
    }
}