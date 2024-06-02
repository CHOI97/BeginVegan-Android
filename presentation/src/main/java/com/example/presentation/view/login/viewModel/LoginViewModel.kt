package com.example.presentation.view.login.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AuthToken
import com.example.domain.useCase.auth.SignInUseCase
import com.example.domain.useCase.auth.SignUpUseCase
import com.example.presentation.auth.User
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
            Timber.d("$result")
        }
    }

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    private val _signupState = MutableLiveData<Result<Unit>>()
    val signupState: LiveData<Result<Unit>> = _signupState

    fun signIn(email: String, providerId: String) {
        viewModelScope.launch {
            signInUseCase.invoke(email, providerId).onSuccess {
                User.accessToken = it.accessToken
                User.refreshToken = it.refreshToken
                _loginState.postValue(true)
            }.onFailure {
                _loginState.postValue(false)
            }
        }
    }
}
