package com.example.data.retrofit.map

import retrofit2.http.GET

interface VeganMapService {
    @GET
    suspend fun getNearByRestaurant(

    )
}