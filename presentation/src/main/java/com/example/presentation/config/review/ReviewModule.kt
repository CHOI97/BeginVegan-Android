package com.example.presentation.config.review

import com.example.domain.useCase.review.ReviewRecommendUseCase
import com.example.presentation.util.ReviewRecommendController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ReviewModule {
    @Provides
    fun provideReviewRecommendController(
        reviewRecommendUseCase: ReviewRecommendUseCase
    ): ReviewRecommendController = object: ReviewRecommendController {
        override suspend fun updateReviewRecommend(reviewId: Int): Boolean {
            var onSuccess = false
            reviewRecommendUseCase.updateReviewRecommend(reviewId).onSuccess {
                onSuccess = true
            }.onFailure {
                onSuccess =false
            }
            return onSuccess
        }
    }
}