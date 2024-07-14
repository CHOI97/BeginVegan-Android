package com.example.presentation.view.main

import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun initViewModel() {
    }

    override fun init() {
        binding.dlDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        setupOnBackPressedCallback()
    }

    private fun setupOnBackPressedCallback(){
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                var navController = findNavController(R.id.fcw_main_container)
                var nowNav:String
                if(navController.currentDestination?.id==R.id.mainFragment){
                    navController = findNavController(R.id.fcw_home)
                    nowNav = "homeNav"
                }else nowNav = "mainNav"
                Timber.d("$nowNav : ${navController.backQueue.size}")

                if(binding.dlDrawer.isDrawerOpen(GravityCompat.END)){
                    binding.dlDrawer.closeDrawer(GravityCompat.END)
                }else if(navController.backQueue.size > 2){
                    navController.popBackStack()
                }else{
                    isEnabled = false
                    finish()
                }
            }
        })
    }
}