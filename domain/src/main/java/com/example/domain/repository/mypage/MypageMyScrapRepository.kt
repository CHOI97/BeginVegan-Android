package com.example.domain.repository.mypage

import com.example.domain.model.mypage.MyReview
import com.example.domain.model.mypage.MypageMyMagazineItem
import com.example.domain.model.mypage.MypageMyRecipeItem
import com.example.domain.model.mypage.MypageMyRestaurantItem
import kotlinx.coroutines.flow.Flow

interface MypageMyScrapRepository {
    suspend fun getMyMagazineList(page: Int): Flow<Result<List<MypageMyMagazineItem>>>
    suspend fun getMyRecipeList(page: Int): Flow<Result<List<MypageMyRecipeItem>>>
    suspend fun getMyRestaurantList(page: Int): Flow<Result<List<MypageMyRestaurantItem>>>
    suspend fun getMyReviewList(page: Int): Flow<Result<List<MyReview>>>
}