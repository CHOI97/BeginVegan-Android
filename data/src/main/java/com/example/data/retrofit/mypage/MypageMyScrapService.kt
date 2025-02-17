package com.example.data.retrofit.mypage

import com.example.data.model.mypage.MyMagazineResponse
import com.example.data.model.mypage.MyRecipeResponse
import com.example.data.model.mypage.MyRestaurantResponse
import com.example.data.model.mypage.MyReviewResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MypageMyScrapService {
    @GET("/api/v1/bookmarks/magazine")
    suspend fun getMyMagazineList(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): ApiResponse<MyMagazineResponse>

    @GET("/api/v1/bookmarks/recipe")
    suspend fun getMyRecipeList(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): ApiResponse<MyRecipeResponse>

    @GET("/api/v1/bookmarks/restaurant")
    suspend fun getMyRestaurantList(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): ApiResponse<MyRestaurantResponse>

    @GET("/api/v1/reviews")
    suspend fun getMyReviewList(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): ApiResponse<MyReviewResponse>
}