package com.example.data.retrofit

import retrofit2.http.GET

interface VeganMapService {
    @GET
    suspend fun getNearByRestaurant(

    )
}