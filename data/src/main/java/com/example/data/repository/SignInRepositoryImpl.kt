package com.example.data.repository

import com.example.domain.useCase.auth.SignInUseCase
import com.example.data.retrofit.UserService
import com.example.domain.repository.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(private val userService: UserService) : SignInRepository {
}
