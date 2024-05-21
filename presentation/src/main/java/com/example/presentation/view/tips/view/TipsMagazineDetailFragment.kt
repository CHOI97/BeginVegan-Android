package com.example.presentation.view.tips.view

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTipsMagazineDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TipsMagazineDetailFragment : BaseFragment<FragmentTipsMagazineDetailBinding>(R.layout.fragment_tips_magazine_detail) {
    override fun init() {
        binding.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}