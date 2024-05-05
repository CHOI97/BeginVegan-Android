package com.example.presentation.view.tips

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTipsMagazineDetailBinding

class TipsMagazineDetailFragment : BaseFragment<FragmentTipsMagazineDetailBinding>(R.layout.fragment_tips_magazine_detail) {
    override fun init() {
        binding.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}