package com.example.domain.repository.bookmarks

interface BookmarkRepository {
    suspend fun postBookmark(contentId:Int, contentType:String): Result<Boolean>
    suspend fun deleteBookmark(contentId:Int, contentType:String): Result<Boolean>
}