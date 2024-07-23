package com.example.data.di.map

import com.example.data.di.core.db.DataStoreModule
import com.example.data.di.core.network.NetworkModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class, DataStoreModule::class])
@InstallIn(SingletonComponent::class)
object VeganMapModule {
}