package com.example.domain.repository.mypage

import com.example.domain.model.mypage.MypageMyMagazineItem
import com.example.domain.model.tips.TipsMagazineItem
import kotlinx.coroutines.flow.Flow

interface MypageMyScrapRepository {
    suspend fun getMyMagazineList(page: Int): Flow<Result<List<MypageMyMagazineItem>>>
}