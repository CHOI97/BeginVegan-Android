package com.example.presentation.view.login.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityLoginBinding
import com.example.presentation.view.login.viewModel.LoginViewModel
import com.example.presentation.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Timer

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel: LoginViewModel by viewModels()

    override fun initViewModel() {
        viewModel.loginState.observe(this) { isLoggedIn ->
            if (isLoggedIn) {
                if(viewModel.additionalInfoProvided){
                    navigateToMainActivity()
                }else{
                    navigateToOnboardingActivity()
                }
            }
        }
    }

    override fun init() {
        setNoticeDialog()
        setOnClickLogin()
    }

    private fun setNoticeDialog(){
        NoticePermissionDialog().show(supportFragmentManager, "PermissionDialog")
    }

    private fun setOnClickLogin() {
        binding.btnLoginKakao.setOnClickListener {
            viewModel.kakaoLogin(this)
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToOnboardingActivity(){
        val intent = Intent(this, OnboardingActivity::class.java)
        startActivity(intent)
    }


}