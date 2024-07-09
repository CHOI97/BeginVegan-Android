package com.example.presentation.config.bookmark

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.bookmarks.BookmarkUseCase
import com.example.presentation.auth.User
import com.example.presentation.util.BookmarkController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Module
@InstallIn(ActivityComponent::class)
object BookmarkModule {

    @Provides
    fun provideBookmarkController(bookmarkUseCase:BookmarkUseCase):BookmarkController = object:BookmarkController{
        override suspend fun postBookmark(contentId:Int, contentType:String): Boolean {
            var onSuccess = false
            coroutineScope {
                bookmarkUseCase.postBookmark(User.accessToken, contentId, contentType).onSuccess {
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
                bookmarkUseCase.deleteBookmark(User.accessToken, contentId, contentType).onSuccess {
                    onSuccess = true
                }.onFailure {
                    onSuccess = false
                }
            }
            return onSuccess
        }
    }
}