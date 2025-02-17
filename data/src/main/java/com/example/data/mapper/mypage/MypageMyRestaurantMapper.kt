package com.example.data.mapper.mypage

import com.example.data.model.mypage.MyRestaurantItemDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.mypage.MypageMyRestaurantItem
import com.example.domain.model.mypage.RestaurantAddress

class MypageMyRestaurantMapper:Mapper<List<MyRestaurantItemDto>,List<MypageMyRestaurantItem>> {
    override fun mapFromEntity(type: List<MyRestaurantItemDto>): List<MypageMyRestaurantItem> {
        return type.map { MypageMyRestaurantItem(
            restaurantId = it.restaurantId,
            thumbnail = it.thumbnail,
            name = it.name,
            restaurantType = it.restaurantType,
            address = it.address.map { RestaurantAddress(
                province = it.province,
                city = it.city,
                roadName = it.roadName,
                detailAddress = it.detailAddress
            ) }
        ) }
    }
}