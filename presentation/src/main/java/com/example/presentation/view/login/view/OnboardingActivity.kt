package com.example.presentation.view.login.view

import android.content.Intent
import androidx.activity.viewModels
import com.example.presentation.R
import com.example.presentation.adapter.onboarding.OnboardingAdapter
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityOnboardingBinding
import com.example.presentation.util.PhotoSelectDialog
import com.example.presentation.view.login.viewModel.OnboardingViewModel
import com.example.presentation.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    val items = listOf(
        "알고 있지 않아요",
        "비건",
        "락토 베지테리언",
        "오보 베지테리언",
        "락토 오보 베지테리언",
        "페스코 베지테리언",
        "폴로 베지테리언",
        "플렉시테리언"
    )

    private lateinit var emptyErrorText: String
    private lateinit var invalidErrorText: String
    private val viewModel: OnboardingViewModel by viewModels()
    override fun initViewModel() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    override fun init() {
        setErrorText()
        setInputHelper()
        setOnClickProfile()
        setOnClickMain()
        setDropdown()
        setObserve()
    }

    private fun setObserve() {
        viewModel.validNickName.observe(this){
            binding.btnOnboardingNext.isEnabled = viewModel.checkValid()
        }
        viewModel.validVeganLevel.observe(this){
            binding.btnOnboardingNext.isEnabled = viewModel.checkValid()
        }
    }

    private fun setErrorText() {
        emptyErrorText = getString(R.string.helper_text_nickname_default)
        invalidErrorText = getString(R.string.helper_text_nickname_check)
    }

    private fun setDropdown() {
        val adapter = OnboardingAdapter(this, R.layout.item_dropdown, items)

        binding.actvOnboardingEditDropdown.setAdapter(adapter)

        binding.actvOnboardingEditDropdown.setOnItemClickListener { parent, view, position, id ->
            adapter.setSelectedItemPosition(position)
            viewModel.setValidVeganLevel()
        }
    }

    private fun setInputHelper() {
        binding.tilOnboardingEditNick.error = emptyErrorText
        viewModel.setValidNickName(false)

        viewModel.nickName.observe(this) { nickName ->
            binding.tilOnboardingEditNick.error = if (nickName.isNullOrEmpty()) {
                // 비어 있는 경우
                viewModel.setValidNickName(false)
                emptyErrorText
            } else {
                if (viewModel.validateNickName(nickName)) {
                    // 유효한 경우
                    viewModel.setValidNickName(true)
                    ""
                } else {
                    // 유효하지 않은 경우
                    viewModel.setValidNickName(false)
                    invalidErrorText
                }
            }
        }
    }

    private fun setOnClickMain() {
        binding.btnOnboardingNext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setOnClickProfile() {
        binding.civOnboardingProfile.onThrottleClick {
            PhotoSelectDialog().show(supportFragmentManager, "PermissionDialog")
        }
    }

}