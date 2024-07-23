package com.example.data.di.veganType

import com.example.data.di.core.db.DataStoreModule
import com.example.data.di.core.network.NetworkModule
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.repository.remote.veganType.VeganTypeRemoteDataSource
import com.example.data.repository.remote.veganType.VeganTypeRemoteDataSourceImpl
import com.example.data.repository.remote.veganType.VeganTypeRepositoryImpl
import com.example.data.retrofit.veganTypes.VeganTypeService
import com.example.domain.repository.veganType.VeganTypeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
object VeganTypeModule {
    @Singleton
    @Provides
    fun provideVeganTypeService(retrofit: Retrofit): VeganTypeService {
        return retrofit.create(VeganTypeService::class.java)
    }

    @Provides
    @Singleton
    fun provideVeganTypeRemoteDataSource(
        veganTypeService: VeganTypeService,
        authTokenDataSource: AuthTokenDataSource
    ): VeganTypeRemoteDataSource {
        return VeganTypeRemoteDataSourceImpl(veganTypeService, authTokenDataSource)
    }

    @Provides
    @Singleton
    fun provideVeganTypeRepository(
        veganTypeRemoteDataSource: VeganTypeRemoteDataSource,
    ): VeganTypeRepository {
        return VeganTypeRepositoryImpl(veganTypeRemoteDataSource)
    }
}