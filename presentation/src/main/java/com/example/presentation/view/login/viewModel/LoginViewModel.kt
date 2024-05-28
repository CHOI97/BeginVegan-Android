package com.example.presentation.view.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.useCase.auth.SignInUseCase
import com.example.domain.useCase.auth.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    fun signUp(email: String, providerId: String) {
        viewModelScope.launch {
            val result = signUpUseCase.invoke(email, providerId)
            Timber.d("${result}")
        }
    }

    fun signIn(email: String, providerId: String) {
        viewModelScope.launch {
            val result = signInUseCase.invoke(email, providerId)
            Timber.d("${result}")
        }
    }
}
