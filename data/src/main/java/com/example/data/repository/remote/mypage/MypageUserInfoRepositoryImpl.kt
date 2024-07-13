package com.example.data.repository.remote.mypage

import com.example.data.mapper.mypage.MypageUserInfoMapper
import com.example.domain.model.mypage.MypageUserInfo
import com.example.domain.repository.mypage.MypageUserInfoRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import timber.log.Timber
import javax.inject.Inject

class MypageUserInfoRepositoryImpl @Inject constructor(
    private val mypageUserInfoRemoteDataSource: MypageUserInfoRemoteDataSource,
    private val mypageUserInfoMapper: MypageUserInfoMapper
):MypageUserInfoRepository{
    override suspend fun getMypageUserInfo(): Result<MypageUserInfo> {
        return try {
            val response = mypageUserInfoRemoteDataSource.getMypageUserInfo()
            when (response) {
                is ApiResponse.Success -> {
                    val magazineList = mypageUserInfoMapper.mapFromEntity(response.data.information)
                    Result.success(magazineList)
                }

                is ApiResponse.Failure.Error -> {
                    Timber.e("getMypageUserInfo error: ${response.errorBody}")
                    Result.failure(Exception("getMypageUserInfo failed"))
                }

                is ApiResponse.Failure.Exception -> {
                    Timber.e("getMypageUserInfo exception: ${response.message}")
                    Result.failure(response.throwable)
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "getMypageUserInfo exception")
            Result.failure(e)
        }
    }
}