package com.example.domain.useCase.mypage

import com.example.domain.model.mypage.MypageMyMagazineItem
import com.example.domain.model.mypage.MypageMyRecipeItem
import com.example.domain.repository.mypage.MypageMyScrapRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MypageMyScrapUseCase @Inject constructor(
    private val mypageMyScrapRepository: MypageMyScrapRepository
){
    suspend fun getMyMagazineList(page: Int): Flow<Result<List<MypageMyMagazineItem>>> =
        mypageMyScrapRepository.getMyMagazineList(page)

    suspend fun getMyRecipeList(page:Int): Flow<Result<List<MypageMyRecipeItem>>> =
        mypageMyScrapRepository.getMyRecipeList(page)
}