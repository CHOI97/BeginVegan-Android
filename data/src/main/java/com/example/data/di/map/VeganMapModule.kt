package com.example.data.di.map

import com.example.data.di.core.db.DataStoreModule
import com.example.data.di.core.network.NetworkModule
import com.example.data.mapper.map.RestaurantDetailMapper
import com.example.data.mapper.map.VeganMapMapper
import com.example.data.mapper.tips.TipsRecipeDetailMapper
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.repository.remote.map.VeganMapRemoteDataSource
import com.example.data.repository.remote.map.VeganMapRemoteDataSourceImpl
import com.example.data.repository.remote.map.VeganMapRepositoryImpl
import com.example.data.retrofit.map.VeganMapService
import com.example.domain.repository.map.VeganMapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
class VeganMapModule {
    @Provides
    @Singleton
    fun provideVeganMapRestaurant(retrofit: Retrofit): VeganMapService {
        return retrofit.create(VeganMapService::class.java)
    }

    @Provides
    @Singleton
    fun provideVeganMapRemoteDataSource(
        veganMapService: VeganMapService,
        authTokenDataSource: AuthTokenDataSource
    ): VeganMapRemoteDataSource {
        return VeganMapRemoteDataSourceImpl(veganMapService, authTokenDataSource)
    }

    @Provides
    @Singleton
    fun provideVeganMapRepository(
        veganMapRemoteDataSource: VeganMapRemoteDataSource,
        veganMapMapper: VeganMapMapper,
        restaurantDetailMapper: RestaurantDetailMapper
    ): VeganMapRepository {
        return VeganMapRepositoryImpl(
            veganMapRemoteDataSource,
            veganMapMapper,
            restaurantDetailMapper
        )
    }

    @Provides
    @Singleton
    fun provideVeganMapMapper(): VeganMapMapper {
        return VeganMapMapper()
    }

    @Provides
    @Singleton
    fun provideRestaurantDetailMapper(): RestaurantDetailMapper {
        return RestaurantDetailMapper()
    }

}