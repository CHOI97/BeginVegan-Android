package com.example.data.di.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.data.di.core.db.DataStoreModule
import com.example.data.mapper.user.UserDataMapper
import com.example.data.repository.local.user.UserDataRepositoryImpl
import com.example.data.repository.local.user.UserDataSource
import com.example.data.repository.local.user.UserDataSourceImpl
import com.example.domain.repository.user.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module(includes = [DataStoreModule::class])
object UserModule {

    @Provides
    @Singleton
    fun provideUserDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("user_data")
        }
    }

    @Provides
    @Singleton
    fun provideUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource {
        return userDataSourceImpl
    }

    @Provides
    @Singleton
    fun provideUserDataRepository(
        userDataSource: UserDataSource,
        userDataMapper: UserDataMapper
    ): UserDataRepository {
        return UserDataRepositoryImpl(userDataSource, userDataMapper)
    }

    @Provides
    @Singleton
    fun provideUserDataMapper(): UserDataMapper {
        return UserDataMapper()
    }

}