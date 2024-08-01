package com.example.data.mapper.map

import com.example.data.model.map.VeganMapRestaurantDto
import com.example.data.model.mypage.MyMagazineItemDto
import com.example.domain.mapper.Mapper
import com.example.domain.model.map.VeganMapRestaurant
import com.example.domain.model.mypage.MypageMyMagazineItem
import java.math.RoundingMode

class VeganMapMapper : Mapper<List<VeganMapRestaurantDto>, List<VeganMapRestaurant>> {
    override fun mapFromEntity(type: List<VeganMapRestaurantDto>): List<VeganMapRestaurant> {
        return type.map {
            val formattedDistance =
                it.distance.toBigDecimal().setScale(3, RoundingMode.HALF_UP).toDouble()
            VeganMapRestaurant(
                id = it.restaurantId,
                name = it.restaurantName,
                rate = it.rate,
                type = it.restaurantType.toString(),
                distance = formattedDistance,
                latitude = it.latitude,
                longitude = it.longitude,
                thumbnail = it.thumbnail
            )
        }
    }
}