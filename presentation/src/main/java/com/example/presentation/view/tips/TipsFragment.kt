package com.example.presentation.view.tips

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainTipsBinding
import com.example.presentation.view.main.MainActivity
import com.google.android.material.tabs.TabLayoutMediator

class TipsFragment: BaseFragment<FragmentMainTipsBinding>(R.layout.fragment_main_tips) {
    override fun init() {
        (activity as MainActivity).setStateToolBar(false)
        binding.ibNotification.setOnClickListener {
            (activity as MainActivity).openNotificationDrawer()
        }

        binding.vpViewpagerArea.adapter = TipsVpAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tlTab, binding.vpViewpagerArea){ tab, position ->
            when(position){
                0 -> tab.text = getString(R.string.magazine)
                1 -> tab.text = getString(R.string.recipe)
            }
        }.attach()
    }
}