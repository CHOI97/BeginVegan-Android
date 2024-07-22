package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.mapper.map.HistorySearchMapper
import com.example.data.repository.local.search.HistorySearchLocalDataSource
import com.example.data.repository.local.search.HistorySearchLocalDataSourceImpl
import com.example.data.repository.local.search.HistorySearchRepositoryImpl
import com.example.data.repository.remote.userInfo.HomeUserInfoDataSource
import com.example.data.repository.remote.userInfo.HomeUserInfoDataSourceImpl
import com.example.data.repository.remote.userInfo.HomeUserInfoRepositoryImpl
import com.example.data.room.HistorySearchDao
import com.example.data.room.HistorySearchDatabase
import com.example.domain.repository.map.HistorySearchRepository
import com.example.domain.repository.userInfo.HomeUserInfoRepository
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
    ): HistorySearchDatabase = HistorySearchDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideHistorySearchDao(database: HistorySearchDatabase): HistorySearchDao = database.historySearchDao()

    @Provides
    @Singleton
    fun provideHistorySearchRepository(
        historyDataSource: HistorySearchLocalDataSource,
        historySearchMapper: HistorySearchMapper
    ): HistorySearchRepository {
        return HistorySearchRepositoryImpl(historyDataSource, historySearchMapper)
    }

    @Provides
    @Singleton
    fun provideHistorySearchLocalDataSource(
        historySearchDao: HistorySearchDao
    ): HistorySearchLocalDataSource {
        return HistorySearchLocalDataSourceImpl(historySearchDao)
    }

    @Provides
    @Singleton
    fun provideHistorySearchMapper(): HistorySearchMapper {
        return HistorySearchMapper()
    }
}
