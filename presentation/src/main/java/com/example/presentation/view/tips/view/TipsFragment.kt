package com.example.presentation.view.tips.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class TipsFragment: BaseFragment<FragmentMainTipsBinding>(R.layout.fragment_main_tips) {

    @Inject
    lateinit var drawerController: DrawerController
    override fun init() {
        setTipsTab()
    }
    private fun setTipsTab() {
        binding.vpViewpagerArea.adapter = TipsVpAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tlTab, binding.vpViewpagerArea){ tab, position ->
            when(position){
                0 -> tab.text = getString(R.string.magazine)
                1 -> tab.text = getString(R.string.recipe)
            }
        }.attach()

        checkFromTest()
        setOpenDrawer()
    }

    //이동
    private fun setOpenDrawer() {
        binding.includedToolbar.ibNotification.setOnClickListener {
            drawerController.openDrawer()
        }
    }

    private fun checkFromTest(){
        val args: TipsFragmentArgs by navArgs()
        if(args.fromTest){
            //나를 위한 레시피
            binding.vpViewpagerArea.post{
                binding.vpViewpagerArea.currentItem = 1
            }
        }
    }


    //Control Back Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnBackPressedCallback()
    }
    private fun setupOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
//                val navHostFragment = childFragmentManager.findFragmentById(R.id.fcw_home) as NavHostFragment
//                val navController = navHostFragment.findNavController()
                Timber.d("Tips onBackPressed")
                if (drawerController.isDrawerOpen()) {
                    drawerController.closeDrawer()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
}