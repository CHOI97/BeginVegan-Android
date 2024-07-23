package com.example.data.di.mypage

import com.example.data.di.core.db.DataStoreModule
import com.example.data.di.core.network.NetworkModule
import com.example.data.mapper.mypage.MypageUserInfoMapper
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.repository.remote.mypage.MypageUserInfoRemoteDataSource
import com.example.data.repository.remote.mypage.MypageUserInfoRemoteDataSourceImpl
import com.example.data.repository.remote.mypage.MypageUserInfoRepositoryImpl
import com.example.data.retrofit.mypage.MypageUserInfoService
import com.example.domain.repository.mypage.MypageUserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
class MypageUserInfoModule {
    @Singleton
    @Provides
    fun provideMypageUserInfoService(retrofit: Retrofit): MypageUserInfoService {
        return retrofit.create(MypageUserInfoService::class.java)
    }

    @Provides
    @Singleton
    fun provideMypageUserInfoDataSource(
        mypageUserInfoService: MypageUserInfoService,
        authTokenDataSource: AuthTokenDataSource
    ): MypageUserInfoRemoteDataSource {
        return MypageUserInfoRemoteDataSourceImpl(mypageUserInfoService, authTokenDataSource)
    }

    @Provides
    @Singleton
    fun provideMypageUserInfoRepository(
        mypageUserInfoRemoteDataSource: MypageUserInfoRemoteDataSource,
        mypageUserInfoMapper: MypageUserInfoMapper
    ): MypageUserInfoRepository {
        return MypageUserInfoRepositoryImpl(
            mypageUserInfoRemoteDataSource,
            mypageUserInfoMapper
        )
    }

    @Provides
    @Singleton
    fun provideMypageUserInfoMapper(): MypageUserInfoMapper {
        return MypageUserInfoMapper()
    }
}