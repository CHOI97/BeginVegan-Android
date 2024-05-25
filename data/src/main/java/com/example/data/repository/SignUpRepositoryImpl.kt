package com.example.data.repository

import com.example.data.model.auth.AuthRequest
import com.example.data.retrofit.UserService
import com.example.domain.repository.SignUpRepository
import com.example.domain.useCase.auth.SignUpUseCase
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val userService: UserService) : SignUpRepository {
}
