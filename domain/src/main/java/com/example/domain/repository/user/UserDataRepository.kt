package com.example.domain.repository.user

import com.example.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    suspend fun setUserData(userProfile: UserProfile)
    fun getUserData(): Flow<UserProfile?>
}