package com.example.data.model.alarms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class PostAlarmResponse(
    @Json(name = "check") val check: Boolean,
    @Json(name = "information") val information: Information
) {
    @JsonClass(generateAdapter = true)
    data class Information(
        @Json(name = "message") val message: String
    )
}
