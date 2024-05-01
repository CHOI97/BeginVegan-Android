package com.example.presentation.view.mypage

import androidx.core.view.isVisible
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageMyRecipeBinding
import com.example.presentation.view.main.MainActivity

class MypageMyRecipeFragment : BaseFragment<FragmentMypageMyRecipeBinding>(R.layout.fragment_mypage_my_recipe) {
    override fun init() {
        (activity as MainActivity).setStateBn(false)

        binding.includedToolbar.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.ibFab.setOnClickListener {
            //클릭시 상단으로 이동
        }

        //empty state
        binding.llEmptyArea.isVisible = true
        binding.btnMoveToRecipe.setOnClickListener {
            //Recipe로 이동
        }
    }
}