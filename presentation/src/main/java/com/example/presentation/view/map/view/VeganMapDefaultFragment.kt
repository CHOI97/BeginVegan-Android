package com.example.presentation.view.map.view

import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainMapDefaultBinding
import com.example.presentation.databinding.FragmentMainMapVeganMapBinding

class VeganMapDefaultFragment :
    BaseFragment<FragmentMainMapDefaultBinding>(R.layout.fragment_main_map_default) {
    override fun init() {

        binding.btnDefault.setOnClickListener {
            findNavController().navigate(R.id.action_veganMapDefaultFragment_to_veganMapSearchFragment)
        }

    }
}