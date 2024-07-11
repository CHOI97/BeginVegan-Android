package com.example.data.repository.remote.mypage

import com.example.data.mapper.mypage.MypageMyMagazineMapper
import com.example.domain.model.mypage.MypageMyMagazineItem
import com.example.domain.repository.mypage.MypageMyScrapRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MypageMyScrapRepositoryImpl @Inject constructor(
    private val mypageMyScrapRemoteDataSource: MypageMyScrapRemoteDataSource,
    private val mypageMyMagazineMapper: MypageMyMagazineMapper
): MypageMyScrapRepository {
    override suspend fun getMyMagazineList(page: Int): Flow<Result<List<MypageMyMagazineItem>>> {
        return flow{
            try {
                val response = mypageMyScrapRemoteDataSource.getMyMagazineList(page)
                when (response) {
                    is ApiResponse.Success -> {
                        val magazineList = mypageMyMagazineMapper.mapFromEntity(response.data.information)
                        emit(Result.success(magazineList))
                    }

                    is ApiResponse.Failure.Error -> {
                        Timber.e("getMyMagazineList error: ${response.errorBody}")
                        emit(Result.failure(Exception("getMyMagazineList failed")))
                    }

                    is ApiResponse.Failure.Exception -> {
                        Timber.e("getMyMagazineList exception: ${response.message}")
                        emit(Result.failure(response.throwable))
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "getMyMagazineList exception")
                emit(Result.failure(e))
            }
        }
    }
}