package com.example.data.repository.remote.map

import com.example.data.model.map.VeganMapRestaurantResponse
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.retrofit.map.VeganMapService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class VeganMapRemoteDataSourceImpl @Inject constructor(
    private val veganMapService: VeganMapService,
    private val authTokenDataSource: AuthTokenDataSource
) : VeganMapRemoteDataSource {

    override suspend fun getNearRestaurantMap(
        page: Int,
        latitude: String,
        longitude: String
    ): ApiResponse<VeganMapRestaurantResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return veganMapService.getNearRestaurantMap(authHeader, page, latitude, longitude)
            .suspendOnSuccess {
                Timber.d("postBookmark successful")
                ApiResponse.Success(data)
            }.suspendOnError {
            Timber.e("postBookmark error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }
}
