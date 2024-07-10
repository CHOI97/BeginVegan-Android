package com.example.data.repository.remote.tips

import com.example.data.mapper.tips.TipsMagazineDetailMapper
import com.example.data.mapper.tips.TipsMagazineMapper
import com.example.data.model.tips.TipsMagazineItemDto
import com.example.domain.model.tips.TipsMagazineDetail
import com.example.domain.model.tips.TipsMagazineItem
import com.example.domain.model.tips.TipsMagazineList
import com.example.domain.repository.tips.TipsMagazineRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import timber.log.Timber
import javax.inject.Inject

class TipsMagazineRepositoryImpl @Inject constructor(
    private val tipsMagazineRemoteDataSource: TipsMagazineRemoteDataSource,
    private val tipsMagazineMapper: TipsMagazineMapper,
    private val tipsMagazineDetailMapper: TipsMagazineDetailMapper
):TipsMagazineRepository {
    override suspend fun getMagazineList(accessToken: String, page: Int): Result<List<TipsMagazineItem>> {
        return try {
            val response = tipsMagazineRemoteDataSource.getMagazineList(accessToken, page)
            when (response) {
                is ApiResponse.Success -> {
                    val magazineList = tipsMagazineMapper.mapFromEntity(response.data.information)
                    Result.success(magazineList)
                }
                is ApiResponse.Failure.Error -> {
                    Timber.e("getMagazineList error: ${response.errorBody}")
                    Result.failure(Exception("getMagazineList failed"))
                }
                is ApiResponse.Failure.Exception -> {
                    Timber.e("getMagazineList exception: ${response.message}")
                    Result.failure(response.throwable)
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "getMagazineList exception")
            Result.failure(e)
        }
    }

    override suspend fun getMagazineDetail(
        accessToken: String,
        id: Int
    ): Result<TipsMagazineDetail> {
        return try {
            val response = tipsMagazineRemoteDataSource.getMagazineDetail(accessToken, id)
            when (response) {
                is ApiResponse.Success -> {
                    val magazineDetail = tipsMagazineDetailMapper.mapFromEntity(response.data.information)
                    Result.success(magazineDetail)
                }
                is ApiResponse.Failure.Error -> {
                    Timber.e("getMagazineDetail error: ${response.errorBody}")
                    Result.failure(Exception("getMagazineDetail failed"))
                }
                is ApiResponse.Failure.Exception -> {
                    Timber.e("getMagazineDetail exception: ${response.message}")
                    Result.failure(response.throwable)
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "getMagazineDetail exception")
            Result.failure(e)
        }
    }

    override suspend fun getHomeMagazine(accessToken: String): Result<List<TipsMagazineItem>> {
        return try {
            val response = tipsMagazineRemoteDataSource.getHomeMagazine(accessToken)
            when (response) {
                is ApiResponse.Success -> {
                    val magazineList = tipsMagazineMapper.mapFromEntity(response.data.information)
                    Result.success(magazineList)
                }
                is ApiResponse.Failure.Error -> {
                    Timber.e("getHomeMagazine error: ${response.errorBody}")
                    Result.failure(Exception("getHomeMagazine failed"))
                }
                is ApiResponse.Failure.Exception -> {
                    Timber.e("getHomeMagazine exception: ${response.message}")
                    Result.failure(response.throwable)
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "getHomeMagazine exception")
            Result.failure(e)
        }
    }

}