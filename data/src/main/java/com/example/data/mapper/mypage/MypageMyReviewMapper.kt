package com.example.data.mapper.mypage

import com.example.data.model.mypage.MyReviewDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.mypage.MyReview

class MypageMyReviewMapper:Mapper<List<MyReviewDto>,List<MyReview>> {
    override fun mapFromEntity(type: List<MyReviewDto>): List<MyReview> {
        return type.map { MyReview(
            reviewId = it.reviewId,
            restaurantName = it.restaurantName,
            date = it.date,
            rate = it.rate,
            images = it.images,
            content = it.content,
            countRecommendation = it.countRecommendation,
            recommendation = it.recommendation
        ) }
    }
}