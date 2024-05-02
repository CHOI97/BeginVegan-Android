package com.example.presentation.view.mypage

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageSettingBinding
import com.example.presentation.view.main.MainActivity

class MypageSettingFragment : BaseFragment<FragmentMypageSettingBinding>(R.layout.fragment_mypage_setting) {
    override fun init() {
        (activity as MainActivity).setStateBn(false)

        binding.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        //로그아웃
        binding.tvLogout.setOnClickListener {

        }

        //계정 삭제
        binding.tvDeleteAccount.setOnClickListener {

        }
    }
}