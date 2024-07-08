package com.example.data.repository.remote.bookmarks

import com.example.data.model.auth.SignUpResponse
import com.example.data.model.bookmarks.BookmarkRequest
import com.skydoves.sandwich.ApiResponse

interface BookmarkRemoteDataSource {
    suspend fun postBookmark(accessToken:String, bookmarkRequest: BookmarkRequest): ApiResponse<SignUpResponse>
    suspend fun deleteBookmark(accessToken:String, bookmarkRequest: BookmarkRequest): ApiResponse<SignUpResponse>
}