package com.example.presentation.view.main

import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
//    @Inject
//    lateinit var mainNavigationHandler: MainNavigationHandler

    override fun initViewModel() {
    }

    override fun init() {
        Timber.d("mainActivity init")
        binding.dlDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        setBottomNav()
        setupOnBackPressedCallback()
    }

    private fun setBottomNav(){
//        val navController = findNavController(R.id.fcw_main_container)
        Timber.d("setBottomNav")
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcw_main_container) as NavHostFragment
        val navController = navHostFragment.navController
//        val navController = mainNavigationHandler.navController()
//        Timber.d("mainNavigationHandler.navController(): ${mainNavigationHandler.navController()}")
        with(binding) {
            bnvMain.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.mainHomeFragment -> bnvMain.menu.findItem(R.id.item_home).isChecked = true
                    R.id.veganMapFragment -> bnvMain.menu.findItem(R.id.item_map).isChecked = true
                    R.id.mainTipsFragment -> bnvMain.menu.findItem(R.id.item_tips).isChecked = true
                    R.id.mainMypageFragment -> bnvMain.menu.findItem(R.id.item_profile).isChecked = true
                }
            }

            bnvMain.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
//                    R.id.item_home -> mainNavigationHandler.navigateToHome()
//                    R.id.item_map ->  mainNavigationHandler.navigateToMap()
//                    R.id.item_tips -> mainNavigationHandler.navigateToTips()
//                    R.id.item_profile -> mainNavigationHandler.navigateToMypage()
                }
                true
            }

        }
    }

    private fun setupOnBackPressedCallback(){
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                var navController = findNavController(R.id.fcw_main_container)
//                var nowNav:String
//                if(navController.currentDestination?.id==R.id.mainFragment){
//                    navController = findNavController(R.id.fcw_home)
//                    nowNav = "homeNav"
//                }else nowNav = "mainNav"
//                Timber.d("$nowNav : ${navController.backQueue.size}")

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