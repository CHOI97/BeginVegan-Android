package com.example.presentation.config.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.presentation.R
import com.example.presentation.view.home.HomeFragmentDirections

class HomeNavigationImpl(private val navController: NavController) : HomeNavigationHandler {

    override fun navigateToHome() {
        val currentId = navController.currentDestination?.id!!
        navController.navigate(R.id.mainHomeFragment, null,
            NavOptions.Builder().setPopUpTo(currentId, true).build())
    }

    override fun navigateToMap() {
        controlBackStack(R.id.veganMapFragment)
    }

    override fun navigateToTips(fromTest: Boolean) {
        if(fromTest){
            val action = HomeFragmentDirections.actionMainHomeFragmentToTipsFragment(fromTest)
            navController.navigate(action)
        }else{
            controlBackStack(R.id.tipsFragment)
        }
    }

    override fun navigateToMypage() {
        controlBackStack(R.id.mainMypageFragment)
    }

    override fun navigationToMypageNotBackStack() {
        navController.navigate(R.id.mainMypageFragment, null,
            NavOptions.Builder().setPopUpTo(navController.currentDestination?.id!!, true).build())
    }

    override fun navigationToTipsNotBackStack() {
        navController.navigate(R.id.tipsFragment, null,
            NavOptions.Builder().setPopUpTo(navController.currentDestination?.id!!, true).build())
//        val action = HomeFragmentDirections.actionMainHomeFragmentToTipsFragment(false)
//        navController.navigate(action)
    }

    private fun controlBackStack(destinationId:Int){
        val currentId = navController.currentDestination?.id!!
        if(currentId == R.id.mainHomeFragment){
            navController.navigate(destinationId)
        }else{
            navController.navigate(destinationId, null,
                NavOptions.Builder().setPopUpTo(currentId, true).build())
        }
    }
}