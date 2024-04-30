package com.example.presentation.view.mypage

import android.opengl.Visibility
import androidx.core.view.isVisible
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageMyReviewBinding
import com.example.presentation.view.main.MainActivity

class MypageMyReviewFragment : BaseFragment<FragmentMypageMyReviewBinding>(R.layout.fragment_mypage_my_review) {
    override fun init() {
        (activity as MainActivity).setStateBn(false)

        binding.includedToolbar.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        //empty state
        //binding.tvMyReviewEmpty.isVisible = true
    }
}