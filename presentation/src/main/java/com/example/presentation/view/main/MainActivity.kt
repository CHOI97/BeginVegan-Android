package com.example.presentation.view.main

import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.view.mypage.MainMypageFragment

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

                R.id.item_recipe -> {

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

}