package com.example.domain.repository.review

interface ReviewRecommendRepository {
    suspend fun updateReviewRecommend(reviewId:Int): Result<Boolean>
}