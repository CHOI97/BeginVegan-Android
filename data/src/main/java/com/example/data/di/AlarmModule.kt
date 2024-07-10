package com.example.data.di

import com.example.data.mapper.alarms.AlarmMapper
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.repository.remote.alarms.AlarmRemoteDataSource
import com.example.data.repository.remote.alarms.AlarmRemoteDataSourceImpl
import com.example.data.repository.remote.alarms.AlarmRepositoryImpl
import com.example.data.retrofit.AlarmService
import com.example.domain.repository.alarms.AlarmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
class AlarmModule {
    @Singleton
    @Provides
    fun provideAlarmService(retrofit: Retrofit): AlarmService {
        return retrofit.create(AlarmService::class.java)
    }

    @Provides
    @Singleton
    fun provideAlarmRemoteDataSource(
        alarmService: AlarmService,
        authTokenDataSource: AuthTokenDataSource
    ): AlarmRemoteDataSource {
        return AlarmRemoteDataSourceImpl(alarmService, authTokenDataSource)
    }

    @Provides
    @Singleton
    fun provideAlarmRepository(
        alarmRemoteDataSource: AlarmRemoteDataSource,
        alarmMapper: AlarmMapper
    ): AlarmRepository {
        return AlarmRepositoryImpl(alarmRemoteDataSource, alarmMapper)
    }

    @Provides
    @Singleton
    fun provideAlarmMapper(): AlarmMapper {
        return AlarmMapper()
    }
}