package com.example.data.di

import com.example.data.repository.remote.veganType.VeganTypeRemoteDataSource
import com.example.data.repository.remote.veganType.VeganTypeRemoteDataSourceImpl
import com.example.data.repository.remote.veganType.VeganTypeRepositoryImlp
import com.example.data.retrofit.VeganTypeService
import com.example.domain.repository.veganType.VeganTypeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object VeganTypeModule {
    @Singleton
    @Provides
    fun provideVeganTypeService(retrofit: Retrofit): VeganTypeService {
        return retrofit.create(VeganTypeService::class.java)
    }

    @Provides
    @Singleton
    fun provideVeganTypeRemoteDataSource(veganTypeService: VeganTypeService): VeganTypeRemoteDataSource {
        return VeganTypeRemoteDataSourceImpl(veganTypeService)
    }

    @Provides
    @Singleton
    fun provideVeganTypeRepository(
        veganTypeRemoteDataSource: VeganTypeRemoteDataSource,
    ): VeganTypeRepository {
        return VeganTypeRepositoryImlp(veganTypeRemoteDataSource)
    }
}