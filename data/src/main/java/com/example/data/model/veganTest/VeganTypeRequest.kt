package com.example.data.model.veganTest

import com.squareup.moshi.Json

data class VeganTypeRequest(
    @Json(name = "veganType")
    val veganType: String
)
