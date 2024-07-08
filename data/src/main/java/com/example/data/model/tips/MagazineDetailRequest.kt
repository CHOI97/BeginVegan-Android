package com.example.data.model.tips

import com.squareup.moshi.Json

data class MagazineDetailRequest(
    @Json(name = "id")
    val id: Int
)
