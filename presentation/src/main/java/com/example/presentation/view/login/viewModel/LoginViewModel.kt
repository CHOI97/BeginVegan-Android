package com.example.presentation.view.login.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AuthToken
import com.example.domain.useCase.auth.SignInUseCase
import com.example.presentation.auth.User
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Timber.d("KaKao Login | CallBack 로그인 실패 $error")
        } else if (token != null) {
            Timber.d("KaKao Login | CallBack 로그인 성공 ${token.accessToken}")
            fetchKakaoUserData()

        }
    }

//    fun signUp(email: String, providerId: String) {
//        viewModelScope.launch {
//            val result = signUpUseCase.invoke(email, providerId)
//            Timber.d("$result")
//        }
//    }

    private val _loginState = MutableLiveData(false)
    val loginState: LiveData<Boolean> = _loginState

    fun signIn(email: String, providerId: String) {
        viewModelScope.launch {
            signInUseCase.invoke(email, providerId).onSuccess {
                User.accessToken = it.accessToken
                User.refreshToken = it.refreshToken
                _loginState.value = true
            }.onFailure {
                _loginState.value = false
            }
        }
    }

    fun kakaoLogin(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            Timber.d("카카오 로그인: isKakaoTalkLoginAvailable ")
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                Timber.d("카카오 로그인: loginWithKakaoTalk ")

                if (error != null) {
                    Timber.d("카카오톡으로 로그인 실패 $error")

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = mCallback)
                } else if (token != null) {
                    Timber.d("카카오톡으로 로그인 성공 ${token.accessToken}")
                    fetchKakaoUserData()
                }
            }
        } else {
            Timber.d("카카오 계정 로그인")
            UserApiClient.instance.loginWithKakaoAccount(context, callback = mCallback)
        }
    }

    private fun fetchKakaoUserData() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Timber.d("KaKao User 사용자 정보 요청 실패 $error")
            } else if (user != null) {
                var scopes = mutableListOf<String>()
                Timber.d(
                    "KaKao User 사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
                )
                user.kakaoAccount?.email?.let { signIn(it,user.id.toString()) }

            }
        }
    }
}
