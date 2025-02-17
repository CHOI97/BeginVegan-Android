package com.example.presentation.config.bookmark

import com.example.domain.useCase.bookmarks.BookmarkUseCase
import com.example.presentation.util.BookmarkController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.coroutineScope

@Module
@InstallIn(ActivityComponent::class)
object BookmarkModule {

    @Provides
    fun provideBookmarkController(bookmarkUseCase:BookmarkUseCase):BookmarkController = object:BookmarkController{
        override suspend fun postBookmark(contentId:Int, contentType:String): Boolean {
            var onSuccess = false
            coroutineScope {
                bookmarkUseCase.postBookmark(contentId, contentType).onSuccess {
                    onSuccess = true
                }.onFailure {
                    onSuccess = false
                }
            }
            return onSuccess
        }

        override suspend fun deleteBookmark(contentId:Int, contentType:String): Boolean {
            var onSuccess = false
            coroutineScope {
                bookmarkUseCase.deleteBookmark(contentId, contentType).onSuccess {
                    onSuccess = true
                }.onFailure {
                    onSuccess = false
                }
            }
            return onSuccess
        }
    }
}