package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.room.HistorySearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDBModule {
    @Singleton
    @Provides
    fun provideHistorySearchDatabase(
        @ApplicationContext context: Context
    ): HistorySearchDatabase = Room
        .databaseBuilder(context.applicationContext, HistorySearchDatabase::class.java, "beginvegan-search-history.db")
        .build()
}