package com.example.data.repository.remote.mypage

import com.example.data.mapper.mypage.MypageMyMagazineMapper
import com.example.data.mapper.mypage.MypageMyRecipeMapper
import com.example.data.mapper.mypage.MypageMyRestaurantMapper
import com.example.domain.model.mypage.MypageMyMagazineItem
import com.example.domain.model.mypage.MypageMyRecipeItem
import com.example.domain.model.mypage.MypageMyRestaurantItem
import com.example.domain.repository.mypage.MypageMyScrapRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class MypageMyScrapRepositoryImpl @Inject constructor(
    private val mypageMyScrapRemoteDataSource: MypageMyScrapRemoteDataSource,
    private val mypageMyMagazineMapper: MypageMyMagazineMapper,
    private val mypageMyRecipeMapper: MypageMyRecipeMapper,
    private val mypageMyRestaurantMapper: MypageMyRestaurantMapper
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

    override suspend fun getMyRecipeList(page: Int): Flow<Result<List<MypageMyRecipeItem>>> {
        return flow{
            try {
                val response = mypageMyScrapRemoteDataSource.getMyRecipeList(page)
                when (response) {
                    is ApiResponse.Success -> {
                        val recipeList = mypageMyRecipeMapper.mapFromEntity(response.data.information)
                        emit(Result.success(recipeList))
                    }

                    is ApiResponse.Failure.Error -> {
                        Timber.e("getMyRecipeList error: ${response.errorBody}")
                        emit(Result.failure(Exception("getMyRecipeList failed")))
                    }

                    is ApiResponse.Failure.Exception -> {
                        Timber.e("getMyRecipeList exception: ${response.message}")
                        emit(Result.failure(response.throwable))
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "getMyRecipeList exception")
                emit(Result.failure(e))
            }
        }
    }

    override suspend fun getMyRestaurantList(page: Int): Flow<Result<List<MypageMyRestaurantItem>>> {
        return flow{
            try {
                val response = mypageMyScrapRemoteDataSource.getMyRestaurantList(page)
                when (response) {
                    is ApiResponse.Success -> {
                        val restaurantList = mypageMyRestaurantMapper.mapFromEntity(response.data.information)
                        emit(Result.success(restaurantList))
                    }

                    is ApiResponse.Failure.Error -> {
                        Timber.e("getMyRestaurantList error: ${response.errorBody}")
                        emit(Result.failure(Exception("getMyRestaurantList failed")))
                    }

                    is ApiResponse.Failure.Exception -> {
                        Timber.e("getMyRestaurantList exception: ${response.message}")
                        emit(Result.failure(response.throwable))
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "getMyRestaurantList exception")
                emit(Result.failure(e))
            }
        }
    }
}