package com.example.data.di

import com.example.data.mapper.mypage.MypageMyMagazineMapper
import com.example.data.repository.local.auth.AuthTokenDataSource
import com.example.data.repository.remote.mypage.MypageMyScrapRemoteDataSource
import com.example.data.repository.remote.mypage.MypageMyScrapRemoteDataSourceImpl
import com.example.data.repository.remote.mypage.MypageMyScrapRepositoryImpl
import com.example.data.retrofit.MypageMyScrapService
import com.example.domain.repository.mypage.MypageMyScrapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
class MypageMyScrapModule {
    @Singleton
    @Provides
    fun provideMyScrapService(retrofit: Retrofit): MypageMyScrapService {
        return retrofit.create(MypageMyScrapService::class.java)
    }

    @Provides
    @Singleton
    fun provideMyScrapDataSource(
        myScrapService: MypageMyScrapService,
        authTokenDataSource: AuthTokenDataSource
    ): MypageMyScrapRemoteDataSource {
        return MypageMyScrapRemoteDataSourceImpl(myScrapService, authTokenDataSource)
    }

    @Provides
    @Singleton
    fun provideMyScrapRepository(
        myScrapRemoteDataSource: MypageMyScrapRemoteDataSource,
        mypageMyMagazineMapper: MypageMyMagazineMapper
    ): MypageMyScrapRepository {
        return MypageMyScrapRepositoryImpl(
            myScrapRemoteDataSource,
            mypageMyMagazineMapper
        )
    }

    @Provides
    @Singleton
    fun provideMypageMyMagazineMapper(): MypageMyMagazineMapper {
        return MypageMyMagazineMapper()
    }
}