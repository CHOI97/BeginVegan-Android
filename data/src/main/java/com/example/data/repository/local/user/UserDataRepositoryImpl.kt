package com.example.data.repository.local.user

import com.example.data.mapper.user.UserDataMapper
import com.example.domain.model.UserProfile
import com.example.domain.repository.user.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userDataMapper: UserDataMapper
) : UserDataRepository {
    override suspend fun setUserData(userProfile: UserProfile) {
        val userData = userDataMapper.mapToEntity(userProfile)
        userDataSource.setUserData(userData)
    }

    override fun getUserData(): Flow<UserProfile?> {
        return userDataSource.getUserData().map { userData ->
            userData?.let { userDataMapper.mapFromEntity(it) }
        }
    }
}