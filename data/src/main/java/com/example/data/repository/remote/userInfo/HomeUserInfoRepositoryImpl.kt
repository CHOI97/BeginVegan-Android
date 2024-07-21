package com.example.data.repository.remote.userInfo

import androidx.datastore.preferences.protobuf.Api
import com.example.data.mapper.userInfo.HomeUserInfoMapper
import com.example.domain.model.userInfo.HomeUserInfo
import com.example.domain.repository.userInfo.HomeUserInfoRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import timber.log.Timber
import javax.inject.Inject

class HomeUserInfoRepositoryImpl @Inject constructor(
    private val homeUserInfoDataSource: HomeUserInfoDataSource,
    private val userInfoMapper: HomeUserInfoMapper
) : HomeUserInfoRepository {
    override suspend fun getHomeUserInfo(): Result<HomeUserInfo> {
        return try {
            when (val response = homeUserInfoDataSource.getHomeUserInfo()) {
                is ApiResponse.Success -> {
                    val userInfo = userInfoMapper.mapFromEntity(response.data.information)
                    Result.success(userInfo)
                }

                is ApiResponse.Failure.Error -> {
                    Timber.e("SignIn error: ${response.errorBody}")
                    Result.failure(Exception("SignIn failed"))
                }

                is ApiResponse.Failure.Exception -> {
                    Timber.e("SignIn exception: ${response.message}")
                    Result.failure(response.throwable)
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Home Userinfo exception")
            Result.failure(e)
        }
    }
}