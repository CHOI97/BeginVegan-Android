package com.example.presentation.view.login

import android.content.Intent
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityOnboardingBinding
import com.example.presentation.util.PhotoSelectDialog
import com.example.presentation.view.main.MainActivity

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    val items = arrayOf("알고 있지 않아요","비건","락토 베지테리언","오보 베지테리언","락토 오보 베지테리언","페스코 베지테리언","폴로 베지테리언","플렉시테리언")
    override fun initViewModel() {
        // 뷰모델 만들기
        // 닉네임 옵저빙
        // 레벨 옵저빙
        // 유효성검사
        // 검사에 따른 버튼활성화
    }

    override fun init() {
        setOnClickProfile()
        setOnClickMain()
        val adapter = ArrayAdapter(this, R.layout.item_dropdown, items)

        binding.actvOnboardingEditDropdown.setAdapter(adapter)

        binding.actvOnboardingEditDropdown.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // 선택된 항목에 대한 처리 추가
        }
    }

    private fun setOnClickMain() {
        binding.btnOnboardingNext.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setOnClickProfile() {
        binding.civOnboardingProfile.onThrottleClick {
            PhotoSelectDialog().show(supportFragmentManager, "PermissionDialog")
        }
    }

}