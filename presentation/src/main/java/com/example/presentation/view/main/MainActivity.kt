package com.example.presentation.view.main

import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initViewModel() {

    }

    override fun init() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcw_main) as NavHostFragment
        val navController = navHostFragment.navController


        binding.bnvMain.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_home -> {
                    navController.popBackStack()
                    navController.navigate(R.id.mainHomeFragment)
                }

                R.id.item_map -> {

                    navController.popBackStack()
                    navController.navigate(R.id.veganMapFragment)
                }

                R.id.item_tips -> {

                    navController.popBackStack()
                    navController.navigate(R.id.tipsFragment)
                }

                R.id.item_profile -> {

                    navController.popBackStack()
                    navController.navigate(R.id.mainMypageFragment)
                }
            }
            true
        }
    }

    //Tool Bar 상태 제어
    fun setStateToolBar(isVisible:Boolean){
        binding.includedToolbar.isVisible = isVisible
    }
    //BottomNav 상태 제어
    fun setStateBn(isVisible: Boolean){
        binding.bnvMain.isVisible = isVisible
    }
}