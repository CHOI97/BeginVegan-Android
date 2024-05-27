package com.example.presentation.view.tips.view

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.tips.TipsNavigationHandler
import com.example.presentation.config.navigation.tips.TipsNavigationImpl
import com.example.presentation.databinding.FragmentMainTipsBinding
import com.example.presentation.util.DrawerController
import com.example.presentation.view.tips.adapter.TipsVpAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TipsFragment: BaseFragment<FragmentMainTipsBinding>(R.layout.fragment_main_tips) {

    @Inject
    lateinit var drawerController: DrawerController
    override fun init() {
        setTipsTab()
        setOpenDrawer()
    }
    private fun setTipsTab() {
        binding.vpViewpagerArea.adapter = TipsVpAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tlTab, binding.vpViewpagerArea){ tab, position ->
            when(position){
                0 -> tab.text = getString(R.string.magazine)
                1 -> tab.text = getString(R.string.recipe)
            }
        }.attach()
    }

    //이동
    private fun setOpenDrawer() {
        binding.includedToolbar.ibNotification.setOnClickListener {
            drawerController.openDrawer()
        }
    }
}