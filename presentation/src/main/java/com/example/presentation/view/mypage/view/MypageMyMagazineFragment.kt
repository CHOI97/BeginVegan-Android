package com.example.presentation.view.mypage.view

import androidx.core.view.isVisible
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageMyMagazineBinding
import com.example.presentation.view.main.MainFragment

class MypageMyMagazineFragment : BaseFragment<FragmentMypageMyMagazineBinding>(R.layout.fragment_mypage_my_magazine) {
    override fun init() {

        binding.includedToolbar.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.ibFab.setOnClickListener {
            //클릭시 상단으로 이동
        }

        //empty state
        binding.llEmptyArea.isVisible = true
        binding.btnMoveToMagazine.setOnClickListener {
            //Recipe로 이동
        }
    }
}