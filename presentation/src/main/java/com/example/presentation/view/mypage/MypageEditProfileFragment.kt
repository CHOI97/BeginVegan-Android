package com.example.presentation.view.mypage

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageEditProfileBinding
import com.example.presentation.view.main.MainActivity

class MypageEditProfileFragment : BaseFragment<FragmentMypageEditProfileBinding>(R.layout.fragment_mypage_edit_profile) {
    override fun init() {
        (activity as MainActivity).setStateBn(false)

        binding.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}