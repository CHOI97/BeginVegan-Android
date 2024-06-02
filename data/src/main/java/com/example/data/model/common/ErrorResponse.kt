package com.example.data.model.common

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val check: Boolean,
    val information: ErrorInformation
)