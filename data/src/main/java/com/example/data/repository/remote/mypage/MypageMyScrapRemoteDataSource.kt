package com.example.data.repository.remote.mypage

import com.example.data.model.mypage.MyMagazineResponse
import com.example.data.model.mypage.MyRecipeResponse
import com.skydoves.sandwich.ApiResponse

interface MypageMyScrapRemoteDataSource {
    suspend fun getMyMagazineList(page: Int): ApiResponse<MyMagazineResponse>
    suspend fun getMyRecipeList(page: Int): ApiResponse<MyRecipeResponse>
}