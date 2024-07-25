package com.example.domain.useCase.mypage

import com.example.domain.model.mypage.MyReview
import com.example.domain.model.mypage.MypageMyMagazineItem
import com.example.domain.model.mypage.MypageMyRestaurantItem
import com.example.domain.model.tips.TipsRecipeListItem
import com.example.domain.repository.mypage.MypageMyScrapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MypageMyScrapUseCase @Inject constructor(
    private val mypageMyScrapRepository: MypageMyScrapRepository
){
    suspend fun getMyMagazineList(page: Int): Flow<Result<List<MypageMyMagazineItem>>> =
        mypageMyScrapRepository.getMyMagazineList(page)

    suspend fun getMyRecipeList(page:Int): Flow<Result<List<TipsRecipeListItem>>> =
        mypageMyScrapRepository.getMyRecipeList(page)

    suspend fun getMyRestaurantList(page: Int):Flow<Result<List<MypageMyRestaurantItem>>> =
        mypageMyScrapRepository.getMyRestaurantList(page)

    suspend fun getMyReviewList(page: Int): Flow<Result<List<MyReview>>> =
        mypageMyScrapRepository.getMyReviewList(page)
}