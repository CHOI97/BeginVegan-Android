package com.example.presentation.config.navigation.home

import androidx.navigation.NavController
import com.example.presentation.R
import com.example.presentation.view.home.HomeFragmentDirections
import com.example.presentation.view.home.veganTest.view.VeganTestResultFragmentDirections
import com.example.presentation.view.tips.view.TipsFragmentArgs
import timber.log.Timber
import javax.inject.Inject

class HomeNavigationImpl(private val navController: NavController) : HomeNavigationHandler {

    override fun navigateToHome() {
        navController.navigate(R.id.mainHomeFragment)
    }

    override fun navigateToMap() {
        navController.navigate(R.id.veganMapFragment)
    }

    override fun navigateToTips(fromTest: Boolean) {
        if(fromTest){
            val action = HomeFragmentDirections.actionMainHomeFragmentToTipsFragment(fromTest)
            navController.navigate(action)
        }else{
            navController.navigate(R.id.tipsFragment)
        }
    }

    override fun navigateToMypage() {
        navController.navigate(R.id.mainMypageFragment)
    }
}