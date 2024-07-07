package com.example.domain.useCase.tips

import com.example.domain.model.tips.TipsMagazineDetail
import com.example.domain.model.tips.TipsMagazineList
import com.example.domain.repository.tips.TipsMagazineRepository
import javax.inject.Inject

class TipsMagazineUseCase @Inject constructor(private val tipsMagazineRepository: TipsMagazineRepository) {

    suspend fun getMagazineList(accessToken: String, page: Int): Result<TipsMagazineList> =
        tipsMagazineRepository.getMagazineList(accessToken, page)

    suspend fun getMagazineDetail(accessToken: String, id:Int):Result<TipsMagazineDetail> =
        tipsMagazineRepository.getMagazineDetail(accessToken, id)

    suspend fun getHomeMagazine(accessToken: String): Result<TipsMagazineList> =
        tipsMagazineRepository.getHomeMagazine(accessToken)
}