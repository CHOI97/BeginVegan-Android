package com.example.domain.repository.bookmarks

interface BookmarkRepository {
    suspend fun postBookmark(accessToken: String, contentId:Int, contentType:String): Result<Boolean>
    suspend fun deleteBookmark(accessToken: String, contentId:Int, contentType:String): Result<Boolean>
}