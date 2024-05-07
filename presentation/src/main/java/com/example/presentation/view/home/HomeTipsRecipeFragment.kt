package com.example.presentation.view.home

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTipsRecipeBinding

class HomeTipsRecipeFragment: BaseFragment<FragmentTipsRecipeBinding>(R.layout.fragment_home_tips_magazine){
    override fun init() {
        showToast("HomeTipsRecipeFragment")
    }

}