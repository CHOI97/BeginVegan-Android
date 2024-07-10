package com.example.domain.repository.tips

import com.example.domain.model.tips.TipsMagazineDetail
import com.example.domain.model.tips.TipsMagazineItem
import com.example.domain.model.tips.TipsMagazineList

interface TipsMagazineRepository {
    suspend fun getMagazineList(page: Int): Result<List<TipsMagazineItem>>
    suspend fun getMagazineDetail(id: Int): Result<TipsMagazineDetail>
    suspend fun getHomeMagazine(): Result<List<TipsMagazineItem>>
}