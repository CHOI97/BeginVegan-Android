package com.example.data.retrofit

import com.example.data.model.review.ReviewRecommendResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewService {
    @POST("/api/v1/reviews/{reviewId}/recommendation")
    suspend fun updateReviewRecommend(
        @Header("Authorization") token: String,
        @Path("reviewId") reviewId: Int
    ): ApiResponse<ReviewRecommendResponse>
}