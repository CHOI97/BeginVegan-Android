package com.example.data.repository

import com.example.data.retrofit.UserService
import com.example.domain.repository.SignUpDetailRepository
import javax.inject.Inject

class SignUpDetailRepositoryImpl @Inject constructor(private val userService: UserService): SignUpDetailRepository {
}