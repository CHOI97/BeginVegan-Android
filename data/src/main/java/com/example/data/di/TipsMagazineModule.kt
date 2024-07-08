package com.example.data.di

import com.example.data.mapper.tips.TipsMagazineDetailMapper
import com.example.data.mapper.tips.TipsMagazineMapper
import com.example.data.repository.remote.tips.TipsMagazineRemoteDataSource
import com.example.data.repository.remote.tips.TipsMagazineRemoteDataSourceImpl
import com.example.data.repository.remote.tips.TipsMagazineRepositoryImpl
import com.example.data.retrofit.TipsMagazineService
import com.example.domain.repository.tips.TipsMagazineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class TipsMagazineModule {
    @Singleton
    @Provides
    fun provideTipsMagazineService(retrofit: Retrofit): TipsMagazineService {
        return retrofit.create(TipsMagazineService::class.java)
    }

    @Provides
    @Singleton
    fun provideTipsMagazineDataSource(tipsMagazineService: TipsMagazineService): TipsMagazineRemoteDataSource {
        return TipsMagazineRemoteDataSourceImpl(tipsMagazineService)
    }

    @Provides
    @Singleton
    fun provideTipsMagazineRepository(
        tipsMagazineRemoteDataSource: TipsMagazineRemoteDataSource,
        tipsMagazineMapper: TipsMagazineMapper,
        tipsMagazineDetailMapper: TipsMagazineDetailMapper
    ): TipsMagazineRepository {
        return TipsMagazineRepositoryImpl(tipsMagazineRemoteDataSource, tipsMagazineMapper, tipsMagazineDetailMapper)
    }

    @Provides
    @Singleton
    fun provideTipsMagazineMapper(): TipsMagazineMapper {
        return TipsMagazineMapper()
    }

    @Provides
    @Singleton
    fun provideTipsMagazineDetailMapper(): TipsMagazineDetailMapper {
        return TipsMagazineDetailMapper()
    }
}