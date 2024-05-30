package com.example.data.model.common

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true, generator = "sealed:type")
sealed class ApiResponse {
    abstract val check: Boolean
}



