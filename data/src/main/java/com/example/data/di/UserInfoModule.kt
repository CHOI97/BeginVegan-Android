package com.example.data.di

import com.example.data.mapper.auth.AuthMapper
import com.example.data.mapper.core.BaseMapper
import com.example.data.repository.remote.auth.AuthRemoteDataSource
import com.example.data.repository.remote.auth.AuthRemoteDataSourceImpl
import com.example.data.repository.remote.auth.AuthRepositoryImpl
import com.example.data.repository.remote.userInfo.SaveUserInfoDataSource
import com.example.data.repository.remote.userInfo.SaveUserInfoDataSourceImpl
import com.example.data.repository.remote.userInfo.SaveUserInfoRepositoryImpl
import com.example.data.retrofit.UserInfoService
import com.example.data.retrofit.UserService
import com.example.domain.repository.auth.AuthRepository
import com.example.domain.repository.userInfo.SaveUserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object UserInfoModule {
    @Singleton
    @Provides
    fun providerUserInfoService(retrofit: Retrofit): UserInfoService {
        return retrofit.create(UserInfoService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserInfoRemoteDataSource(userService: UserInfoService): SaveUserInfoDataSource {
        return SaveUserInfoDataSourceImpl(userService)
    }

    @Provides
    fun provideAuthRepository(
        saveUserInfoDataSource: SaveUserInfoDataSource,
        baseMapper: BaseMapper
    ): SaveUserInfoRepository {
        return SaveUserInfoRepositoryImpl(saveUserInfoDataSource, baseMapper)
    }

    @Provides
    fun providerBaseMapper(): BaseMapper {
        return BaseMapper()
    }
}