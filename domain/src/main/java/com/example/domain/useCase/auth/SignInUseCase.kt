package com.example.domain.useCase.auth

import com.example.domain.model.auth.AuthToken
import com.example.domain.repository.auth.AuthRepository
import javax.inject.Inject

/** 로그인 UseCase **/
class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, providerId: String): Result<AuthToken> =
        authRepository.signIn(email, providerId)
}