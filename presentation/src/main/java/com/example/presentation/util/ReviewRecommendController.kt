package com.example.presentation.util

interface ReviewRecommendController {
    suspend fun updateReviewRecommend(reviewId:Int) : Boolean
}