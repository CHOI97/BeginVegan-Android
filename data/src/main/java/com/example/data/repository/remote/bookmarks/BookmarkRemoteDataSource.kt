package com.example.data.repository.remote.bookmarks

import com.example.data.model.bookmarks.BookmarkRequest
import com.example.data.model.core.BaseResponse
import com.skydoves.sandwich.ApiResponse

interface BookmarkRemoteDataSource {
    suspend fun postBookmark(accessToken:String, bookmarkRequest: BookmarkRequest): ApiResponse<BaseResponse>
    suspend fun deleteBookmark(accessToken:String, bookmarkRequest: BookmarkRequest): ApiResponse<BaseResponse>
}