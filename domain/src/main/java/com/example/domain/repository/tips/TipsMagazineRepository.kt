package com.example.domain.repository.tips

import com.example.domain.model.tips.TipsMagazineDetail
import com.example.domain.model.tips.TipsMagazineList

interface TipsMagazineRepository {
    suspend fun getMagazineList(accessToken:String, page: Int): Result<TipsMagazineList>
    suspend fun getMagazineDetail(accessToken: String, id: Int): Result<TipsMagazineDetail>
    suspend fun getHomeMagazine(accessToken: String): Result<TipsMagazineList>
}