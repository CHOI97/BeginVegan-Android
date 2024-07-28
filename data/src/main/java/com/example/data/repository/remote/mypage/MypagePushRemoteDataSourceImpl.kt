package com.example.data.repository.remote.mypage

import com.example.data.model.mypage.MypagePushResponse
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.retrofit.mypage.MypagePushService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class MypagePushRemoteDataSourceImpl @Inject constructor(
    private val mypagePushService: MypagePushService,
    private val authTokenDataSource: AuthTokenDataSource,
):MypagePushRemoteDataSource {
    override suspend fun patchPush(): ApiResponse<MypagePushResponse> {
        val accessToken = authTokenDataSource.accessToken.first()
        val authHeader = "Bearer $accessToken"
        return mypagePushService.patchPush(authHeader).suspendOnSuccess {
            Timber.d("patchPush successful")
            ApiResponse.Success(this.data)
        }.suspendOnError {
            Timber.e("patchPush error: ${this.errorBody}")
            ApiResponse.Failure.Error(this.errorBody)
        }
    }
}