package com.example.presentation.view.mypage

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageMyReviewBinding
import com.example.presentation.view.main.MainFragment

class MypageMyReviewFragment : BaseFragment<FragmentMypageMyReviewBinding>(R.layout.fragment_mypage_my_review) {
    override fun init() {

        binding.includedToolbar.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        //empty state
        //binding.tvMyReviewEmpty.isVisible = true
    }
}