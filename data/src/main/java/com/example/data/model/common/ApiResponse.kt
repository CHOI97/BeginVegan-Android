package com.example.data.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true, generator = "sealed:type")
sealed class ApiResponse {
    abstract val check: Boolean
}

@JsonClass(generateAdapter = true)
data class SuccessResponse(
    override val check: Boolean,
    val information: Information
) : ApiResponse()

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    override val check: Boolean,
    val information: ErrorInformation
) : ApiResponse()

@JsonClass(generateAdapter = true)
data class Information(
    val message: String? = null,
    val authRes: AuthRes? = null,
    val signUpCompleted: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class AuthRes(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String
)

@JsonClass(generateAdapter = true)
data class ErrorInformation(
    val timestamp: String,
    val message: String,
    val code: String?,
    val status: Int,
    @Json(name = "class") val className: String?,
    val errors: List<Any>
)