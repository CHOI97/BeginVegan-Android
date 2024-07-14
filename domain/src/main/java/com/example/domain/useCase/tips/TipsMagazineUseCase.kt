package com.example.domain.useCase.tips

import com.example.domain.model.tips.TipsMagazineDetail
import com.example.domain.model.tips.TipsMagazineItem
import com.example.domain.repository.tips.TipsMagazineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TipsMagazineUseCase @Inject constructor(private val tipsMagazineRepository: TipsMagazineRepository) {

    suspend fun getMagazineList(page: Int): Flow<Result<List<TipsMagazineItem>>> =
        tipsMagazineRepository.getMagazineList(page)

    suspend fun getMagazineDetail(id: Int): Result<TipsMagazineDetail> =
        tipsMagazineRepository.getMagazineDetail(id)

    suspend fun getHomeMagazine(): Result<List<TipsMagazineItem>> =
        tipsMagazineRepository.getHomeMagazine()
}