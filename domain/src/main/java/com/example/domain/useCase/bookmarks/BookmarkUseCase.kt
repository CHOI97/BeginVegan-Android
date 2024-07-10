package com.example.domain.useCase.bookmarks

import com.example.domain.repository.bookmarks.BookmarkRepository
import javax.inject.Inject

class BookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend fun postBookmark(accessToken:String, contentId:Int, contentType:String): Result<Boolean> =
        bookmarkRepository.postBookmark(accessToken, contentId, contentType)

    suspend fun deleteBookmark(accessToken:String, contentId:Int, contentType:String): Result<Boolean> =
        bookmarkRepository.deleteBookmark(accessToken, contentId, contentType)
}