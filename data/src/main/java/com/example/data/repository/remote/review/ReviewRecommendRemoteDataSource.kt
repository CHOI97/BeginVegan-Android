package com.example.data.repository.remote.review

import com.example.data.model.review.ReviewRecommendResponse
import com.skydoves.sandwich.ApiResponse

interface ReviewRecommendRemoteDataSource {
    suspend fun updateReviewRecommend(reviewId:Int): ApiResponse<ReviewRecommendResponse>
}