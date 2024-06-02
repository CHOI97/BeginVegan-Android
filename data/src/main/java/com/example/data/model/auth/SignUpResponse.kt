package com.example.data.model.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignUpResponse(
    @Json(name = "check") val check: Boolean,
    @Json(name = "information") val information: Information
) {

    @JsonClass(generateAdapter = true)
    data class Information(
        @Json(name = "message") val message: String
    )
}