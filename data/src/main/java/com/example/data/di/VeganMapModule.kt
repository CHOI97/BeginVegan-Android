package com.example.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class,DataStoreModule::class])
@InstallIn(SingletonComponent::class)
object VeganMapModule {
}