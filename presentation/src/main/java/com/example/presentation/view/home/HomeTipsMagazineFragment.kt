package com.example.presentation.view.home

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentHomeTipsMagazineBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTipsMagazineFragment: BaseFragment<FragmentHomeTipsMagazineBinding>(R.layout.fragment_home_tips_magazine){
    override fun init() {
        showToast("HomeTipsMagazineFragment")
    }

}