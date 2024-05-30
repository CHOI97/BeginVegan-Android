package com.example.data.model.common

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SuccessResponse<T>(
    override val check: Boolean,
    val information: T
) : ApiResponse()