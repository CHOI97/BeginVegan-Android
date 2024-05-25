package com.example.presentation.config.navigation.home

import androidx.navigation.NavController
import com.example.presentation.R
import timber.log.Timber
import javax.inject.Inject

class HomeNavigationImpl(private val navController: NavController) : HomeNavigationHandler {

    override fun navigateToHome() {
        navController.navigate(R.id.mainHomeFragment)
    }

    override fun navigateToMap() {
        navController.navigate(R.id.veganMapFragment)
    }

    override fun navigateToTips() {
        navController.navigate(R.id.tipsFragment)
    }

    override fun navigateToMypage() {
        navController.navigate(R.id.mainMypageFragment)
    }
}