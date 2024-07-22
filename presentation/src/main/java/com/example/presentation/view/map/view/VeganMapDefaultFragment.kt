package com.example.presentation.view.map.view

import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMapDefaultBinding

class VeganMapDefaultFragment :
    BaseFragment<FragmentMapDefaultBinding>(R.layout.fragment_map_default) {
    override fun init() {
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_veganMapDefaultFragment_to_veganMapSearchFragment)
        }
    }
}