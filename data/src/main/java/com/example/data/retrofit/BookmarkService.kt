package com.example.data.retrofit

import com.example.data.model.auth.SignUpResponse
import com.example.data.model.bookmarks.BookmarkRequest
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST

interface BookmarkService {
    @POST("/api/v1/bookmarks")
    suspend fun postBookmark(
        @Header("Authorization") token: String,
        @Body bookmarkRequest: BookmarkRequest
    ): ApiResponse<SignUpResponse>

    @HTTP(method = "DELETE", path = "/api/v1/bookmarks", hasBody = true)
    suspend fun deleteBookmark(
        @Header("Authorization") token: String,
        @Body bookmarkRequest: BookmarkRequest
    ): ApiResponse<SignUpResponse>
}