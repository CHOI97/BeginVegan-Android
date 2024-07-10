package com.example.data.repository.remote.bookmarks

import com.example.data.model.auth.SignUpResponse
import com.example.data.model.bookmarks.BookmarkRequest
import com.example.data.retrofit.BookmarkService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.retrofit.errorBody
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import timber.log.Timber
import javax.inject.Inject

class BookmarkRemoteDataSourceImpl @Inject constructor(
    private val bookmarkService: BookmarkService
):BookmarkRemoteDataSource {
    override suspend fun postBookmark(
        accessToken: String,
        bookmarkRequest: BookmarkRequest
    ): ApiResponse<SignUpResponse> {
        return bookmarkService.postBookmark(accessToken, bookmarkRequest).suspendOnSuccess {
            Timber.d("postBookmark successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("postBookmark error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }

    override suspend fun deleteBookmark(
        accessToken: String,
        bookmarkRequest: BookmarkRequest
    ): ApiResponse<SignUpResponse> {
        return bookmarkService.deleteBookmark(accessToken, bookmarkRequest).suspendOnSuccess {
            Timber.d("deleteBookmark successful")
            ApiResponse.Success(data)
        }.suspendOnError {
            Timber.e("deleteBookmark error: ${this.errorBody}")
            ApiResponse.Failure.Error(this)
        }
    }
}