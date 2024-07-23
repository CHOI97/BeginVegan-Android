package com.example.data.di.auth

import com.example.data.di.core.db.DataStoreModule
import com.example.data.di.core.network.NetworkModule
import com.example.data.mapper.auth.AuthMapper
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.repository.remote.auth.AuthRepositoryImpl
import com.example.data.repository.remote.auth.AuthRemoteDataSource
import com.example.data.repository.remote.auth.AuthRemoteDataSourceImpl
import com.example.data.retrofit.auth.UserService
import com.example.domain.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(userService: UserService): AuthRemoteDataSource {
        return AuthRemoteDataSourceImpl(userService)
    }

    @Provides
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
        authTokenDataSource: AuthTokenDataSource,
        authMapper: AuthMapper
    ): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSource, authTokenDataSource,authMapper)
    }

    @Provides
    fun provideAuthMapper(): AuthMapper {
        return AuthMapper()
    }
}