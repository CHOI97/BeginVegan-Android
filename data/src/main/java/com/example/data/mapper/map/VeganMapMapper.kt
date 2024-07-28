package com.example.data.mapper.map

import com.example.data.model.map.VeganMapRestaurantDto
import com.example.data.model.mypage.MyMagazineItemDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.map.VeganMapRestaurant
import com.example.domain.model.mypage.MypageMyMagazineItem

class VeganMapMapper : Mapper<List<VeganMapRestaurantDto>, List<VeganMapRestaurant>> {
    override fun mapFromEntity(type: List<VeganMapRestaurantDto>): List<VeganMapRestaurant> {
        return type.map {
            VeganMapRestaurant(
                id = it.restaurantId,
                name = it.restaurantName,
                rate = it.rate,
                type = it.restaurantType,
                distance = it.distance,
                latitude = it.latitude,
                longitude = it.longitude,
                thumbnail = it.thumbnail
            )
        }
    }
}