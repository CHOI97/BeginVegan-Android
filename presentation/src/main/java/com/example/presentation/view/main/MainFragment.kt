package com.example.presentation.view.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.home.HomeNavigationHandler
import com.example.presentation.config.navigation.home.HomeNavigationImpl
import com.example.presentation.databinding.FragmentMainBinding
import com.example.presentation.util.DrawerController
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private lateinit var homeNavigationHandler: HomeNavigationHandler
    @Inject
    lateinit var drawerController: DrawerController
    private lateinit var navController:NavController

    override fun init() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fcw_home) as NavHostFragment
        navController = navHostFragment.findNavController()
        homeNavigationHandler = HomeNavigationImpl(navController)
        checkFromOthers()

        with(binding) {
            bnvMain.setupWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.mainHomeFragment -> bnvMain.menu.findItem(R.id.item_home).isChecked = true
                    R.id.veganMapFragment -> bnvMain.menu.findItem(R.id.item_map).isChecked = true
                    R.id.tipsFragment -> bnvMain.menu.findItem(R.id.item_tips).isChecked = true
                    R.id.mainMypageFragment -> bnvMain.menu.findItem(R.id.item_profile).isChecked = true
                }
            }

            bnvMain.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.item_home -> homeNavigationHandler.navigateToHome()
                    R.id.item_map ->  homeNavigationHandler.navigateToMap()
                    R.id.item_tips -> homeNavigationHandler.navigateToTips(false)
                    R.id.item_profile -> homeNavigationHandler.navigateToMypage()
                }
                true
            }

        }
        setupOnBackPressedCallback()
    }

    private fun checkFromOthers(){
        val args: MainFragmentArgs by navArgs()
        if(args.fromTest) {
            homeNavigationHandler.navigateToTips(true)
            val nArg = this.arguments ?:Bundle()
            nArg.putBoolean("fromTest", false)
            this.arguments = nArg

        }else if(args.fromMyMagazine){
            homeNavigationHandler.navigateToTips(false)
            val nArg = this.arguments ?:Bundle()
            nArg.putBoolean("fromMyMagazine", false)
            this.arguments = nArg
        } else{
            if(navController.currentDestination?.id==R.id.mainMypageFragment)
                homeNavigationHandler.navigationToMypageNotBackStack()

            if(navController.currentDestination?.id==R.id.tipsFragment)
                homeNavigationHandler.navigationToTipsNotBackStack()
        }
    }

    private fun setupOnBackPressedCallback(){
        requireActivity().onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if(drawerController.isDrawerOpen()){
                    drawerController.closeDrawer()
                }else if(navController.backQueue.size > 2){
                    isEnabled = false
                    navController.popBackStack()
                }else{
                    isEnabled = false
                    requireActivity().finish()
                }
            }
        })
    }
}