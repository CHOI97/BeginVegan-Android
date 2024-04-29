package com.example.presentation.view.login

import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityOnboardingBinding
import com.example.presentation.util.PhotoSelectDialog

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    override fun initViewModel() {
    }

    override fun init() {
        setOnClickProfile()
    }

    private fun setOnClickProfile() {
        binding.civOnboardingProfile.onThrottleClick {
            PhotoSelectDialog().show(supportFragmentManager, "PermissionDialog")
        }
    }

}