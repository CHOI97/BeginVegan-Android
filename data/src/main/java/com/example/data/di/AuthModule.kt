package com.example.data.di

import com.example.data.repository.AuthRepositoryImpl
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

    @Singleton
    @Provides
    fun provideAuthRepository(userService: UserService) : AuthRepository {
        return AuthRepositoryImpl(userService)
    }

}