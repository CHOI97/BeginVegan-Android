package com.example.data.retrofit.map

import retrofit2.http.GET

interface VeganMapService {
    @GET("/api/v1/restaurants/{restaurant-id}")
    suspend fun getRestaurantDetail(

    )
}