package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.mapper.map.HistorySearchMapper
import com.example.data.mapper.userInfo.HomeUserInfoMapper
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
    ): HistorySearchDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            HistorySearchDatabase::class.java,
            "beginvegan-search-history.db"
        )
        .build()

    @Provides
    @Singleton
    fun provideHistorySearchDao(database: HistorySearchDatabase) = database.historySearchDao()

    @Provides
    fun provideHistorySearchRepository(
        historyDataSource: HistorySearchLocalDataSource,
        historySearchMapper: HistorySearchMapper
    ): HistorySearchRepository {
        return HistorySearchRepositoryImpl(historyDataSource, historySearchMapper)
    }

    @Provides
    fun provideHistorySearchLocalDataSource(
        historySearchDao: HistorySearchDao
    ): HistorySearchLocalDataSource {
        return HistorySearchLocalDataSourceImpl(historySearchDao)
    }

    @Provides
    fun provideHistorySearchMapper(): HistorySearchMapper{
        return HistorySearchMapper()
    }
}