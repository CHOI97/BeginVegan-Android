package com.example.presentation.view.login.view

import android.content.Intent
import androidx.activity.viewModels
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityLoginBinding
import com.example.presentation.view.login.viewModel.LoginViewModel
import com.example.presentation.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


//
//        binding.btnLoginKakao.setOnClickListener {
//            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
//                // 카카오톡 로그인
//                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
//                    // 로그인 실패 부분
//                    if (error != null) {
//                        Log.e("KaKao Login", "로그인 실패 $error")
//                        // 사용자가 취소
//                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
//                            Log.e("KaKao Login", "로그인 실패 사용자 취소")
//                            return@loginWithKakaoTalk
//                        }
//                        // 다른 오류
//                        else {
//                            UserApiClient.instance.loginWithKakaoAccount(
//                                this,
//                                callback = mCallback
//                            ) // 카카오 이메일 로그인
//                            Log.e("KaKao Login", "예외 오류")
//                        }
//                    }
//                    // 로그인 성공 부분
//                    else if (token != null) {
//                        Log.e("KaKao Login", "로그인 성공 ${token.accessToken}")
//                        showLoadingDialog(this)
//                        getKakaoUserData()
//                    }
//                }
//            } else {
//                Log.e("KaKao Login", "이메일 로그인")
//                UserApiClient.instance.loginWithKakaoAccount(
//                    this,
//                    callback = mCallback
//                ) // 카카오 이메일 로그인
//            }
//        }
//    }

//    private fun getKakaoUserData() {
//        UserApiClient.instance.me { user, error ->
//            if (error != null) {
//                Log.e("KaKao User", "사용자 정보 요청 실패", error)
//            } else if (user != null) {
//                Log.i(
//                    "KaKao User", "사용자 정보 요청 성공" +
//                            "\n회원번호: ${user.id}" +
//                            "\n이메일: ${user.kakaoAccount?.email}" +
//                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
//                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"
//                )
//                mAuth = KakaoAuth(
//                    (user.id).toString()!!,
//                    user.kakaoAccount?.profile?.nickname!!,
//                    user.kakaoAccount?.email!!,
//                    user.kakaoAccount?.profile?.thumbnailImageUrl!!
//                )
//                AuthSignService(this).tryPostAuthSignIn(mAuth.providerId,mAuth.email)
//            }
//        }
//    }

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel: LoginViewModel by viewModels()
    override fun initViewModel() {
        viewModel.loginState.observe(this){
            when(it){
                true -> {
                    showToast("Test: 로그인 성공")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                false ->{
                    showToast("Test: 로그인 에러")
                }
            }
        }
    }

    override fun init() {
        PermissionDialog().show(supportFragmentManager, "PermissionDialog")
        setOnClickLogin()
    }

    private fun setOnClickLogin() {
        binding.btnLoginKakao.setOnClickListener {
            viewModel.kakaoLogin(this)
        }
    }
}