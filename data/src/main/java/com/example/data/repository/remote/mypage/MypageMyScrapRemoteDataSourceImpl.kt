package com.example.data.repository.remote.mypage

import com.example.data.model.mypage.MyMagazineResponse
import com.example.data.model.mypage.MyRecipeResponse
import com.example.data.model.mypage.MyRestaurantResponse
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.retrofit.MypageMyScrapService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class MypageMyScrapRemoteDataSourceImpl @Inject constructor(
    private val mypageMyScrapService: MypageMyScrapService,
    private val authTokenDataSource: AuthTokenDataSource,
):MypageMyScrapRemoteDataSource {
    override suspend fun getMyMagazineList(page: Int): ApiResponse<MyMagazineResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return mypageMyScrapService.getMyMagazineList(authHeader, page).suspendOnSuccess {
            Timber.d("getMyMagazineList successful")
            ApiResponse.Success(this.data)
        }.suspendOnError {
            Timber.e("getMyMagazineList error: ${this.errorBody}")
            ApiResponse.Failure.Error(this.errorBody)
        }
    }

    override suspend fun getMyRecipeList(page: Int): ApiResponse<MyRecipeResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return mypageMyScrapService.getMyRecipeList(authHeader, page).suspendOnSuccess {
            Timber.d("getMyRecipeList successful")
            ApiResponse.Success(this.data)
        }.suspendOnError {
            Timber.e("getMyRecipeList error: ${this.errorBody}")
            ApiResponse.Failure.Error(this.errorBody)
        }
    }

    override suspend fun getMyRestaurantList(page: Int): ApiResponse<MyRestaurantResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return mypageMyScrapService.getMyRestaurantList(authHeader, page).suspendOnSuccess {
            Timber.d("getMyRestaurantList successful")
            ApiResponse.Success(this.data)
        }.suspendOnError {
            Timber.e("getMyRestaurantList error: ${this.errorBody}")
            ApiResponse.Failure.Error(this.errorBody)
        }
    }
}