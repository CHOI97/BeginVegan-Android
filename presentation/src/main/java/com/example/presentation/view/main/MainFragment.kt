package com.example.presentation.view.main

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.util.DrawerController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {


    override fun init() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fcw_main) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvMain.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.mainHomeFragment -> binding.bnvMain.menu.findItem(R.id.item_home).isChecked = true
                R.id.veganMapFragment -> binding.bnvMain.menu.findItem(R.id.item_map).isChecked = true
                R.id.tipsFragment -> binding.bnvMain.menu.findItem(R.id.item_tips).isChecked = true
                R.id.mainMypageFragment -> binding.bnvMain.menu.findItem(R.id.item_profile).isChecked = true
            }
        }

        binding.bnvMain.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_home -> {
                    navController.popBackStack()
                    navController.navigate(R.id.mainHomeFragment)
                }

                R.id.item_map -> {
                    navController.navigate(R.id.veganMapFragment)
                }

                R.id.item_tips -> {
                    navController.navigate(R.id.tipsFragment)
                }

                R.id.item_profile -> {
                    navController.navigate(R.id.mainMypageFragment)
                }
            }
            true
        }

    }

}