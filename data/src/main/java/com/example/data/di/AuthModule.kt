package com.example.data.di

import com.example.data.mapper.auth.AuthMapper
import com.example.data.repository.remote.auth.AuthRepositoryImpl
import com.example.data.repository.remote.auth.AuthRemoteDataSource
import com.example.data.repository.remote.auth.AuthRemoteDataSourceImpl
import com.example.data.retrofit.UserService
import com.example.domain.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class AuthModule {
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
        authMapper: AuthMapper
    ): AuthRepository {
        return AuthRepositoryImpl(authRemoteDataSource, authMapper)
    }

    @Provides
    fun provideAuthMapper(): AuthMapper {
        return AuthMapper()
    }
}