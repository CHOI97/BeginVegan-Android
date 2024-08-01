package com.example.data.repository.remote.map

import com.example.data.mapper.map.VeganMapMapper
import com.example.domain.model.map.VeganMapRestaurant
import com.example.domain.repository.map.VeganMapRepository
import com.skydoves.sandwich.ApiResponse
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
    ): Flow<List<VeganMapRestaurant>> {
        return flow {
            try {
                val response =
                    veganMapRemoteDataSource.getNearRestaurantMap(page, latitude, longitude)
                when (response) {
                    is ApiResponse.Success -> {
                        Timber.e("Success fetching restaurants: $response")
                        val restaurants = veganMapMapper.mapFromEntity(response.data.information)
                        emit(restaurants)
                    }

                    is ApiResponse.Failure.Error -> {
                        Timber.e("Error fetching restaurants: ${response.errorBody}")
                        emit(emptyList())
                    }

                    is ApiResponse.Failure.Exception -> {
                        Timber.e("getNearRestaurantMap exception: ${response.message}")
                        emit(emptyList())
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "getMagazineList exception")
                emit(emptyList())
            }
        }
    }


    override suspend fun getRestaurantDetail() {
    }
}