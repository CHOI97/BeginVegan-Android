package com.example.presentation.view.home

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentVeganTestBinding
import com.example.presentation.view.main.MainActivity

class VeganTestFragment : BaseFragment<FragmentVeganTestBinding>(R.layout.fragment_vegan_test) {
    override fun init() {
        (activity as MainActivity).setStateToolBar(false)
        (activity as MainActivity).setStateBn(false)

        binding.includedToolbar.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}