package com.example.presentation.view.main

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.util.DrawerController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initViewModel() {
    }

    override fun init() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcw_home) as NavHostFragment
        val navController = navHostFragment.navController

        binding.dlDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//        navController.navigate(R.id.mainHomeFragment)
    }
}