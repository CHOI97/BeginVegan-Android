package com.example.presentation.view.tips.view

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentTipsMagazineDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TipsMagazineDetailFragment : BaseFragment<FragmentTipsMagazineDetailBinding>(R.layout.fragment_tips_magazine_detail) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler

    override fun init() {
        goBackUp()
    }

    private fun goBackUp(){
        binding.ibBackUp.setOnClickListener {
            mainNavigationHandler.popBackStack()
        }
    }
}