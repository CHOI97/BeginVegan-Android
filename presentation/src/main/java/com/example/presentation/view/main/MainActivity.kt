package com.example.presentation.view.main

import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.config.navigation.home.HomeNavigationHandler
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.view.home.veganTest.viewModel.VeganTestViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initViewModel() {
    }
    override fun init() {
        binding.dlDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
}