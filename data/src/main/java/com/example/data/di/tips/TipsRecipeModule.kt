package com.example.data.di.tips

import com.example.data.di.core.db.DataStoreModule
import com.example.data.di.core.network.NetworkModule
import com.example.data.mapper.tips.TipsRecipeDetailMapper
import com.example.data.mapper.tips.TipsRecipeMapper
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.repository.remote.tips.TipsRecipeRemoteDataSource
import com.example.data.repository.remote.tips.TipsRecipeRemoteDataSourceImpl
import com.example.data.repository.remote.tips.TipsRecipeRepositoryImpl
import com.example.data.retrofit.tips.TipsRecipeService
import com.example.domain.repository.tips.TipsRecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
class TipsRecipeModule {
    @Singleton
    @Provides
    fun provideTipsRecipeService(retrofit: Retrofit): TipsRecipeService {
        return retrofit.create(TipsRecipeService::class.java)
    }

    @Provides
    @Singleton
    fun provideTipsRecipeRemoteDataSource(
        tipsRecipeService: TipsRecipeService,
        authTokenDataSource: AuthTokenDataSource,
    ): TipsRecipeRemoteDataSource {
        return TipsRecipeRemoteDataSourceImpl(tipsRecipeService, authTokenDataSource)
    }

    @Provides
    @Singleton
    fun provideTipsRecipeRepository(
        tipsRecipeRemoteDataSource: TipsRecipeRemoteDataSource,
        tipsRecipeMapper: TipsRecipeMapper,
        tipsRecipeDetailMapper: TipsRecipeDetailMapper
    ): TipsRecipeRepository {
        return TipsRecipeRepositoryImpl(
            tipsRecipeRemoteDataSource,
            tipsRecipeMapper,
            tipsRecipeDetailMapper
        )
    }

    @Provides
    @Singleton
    fun provideTipsRecipeMapper(): TipsRecipeMapper {
        return TipsRecipeMapper()
    }

    @Provides
    @Singleton
    fun provideTipsRecipeDetailMapper(): TipsRecipeDetailMapper {
        return TipsRecipeDetailMapper()
    }
}