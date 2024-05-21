package com.example.presentation.config.navigation.home

import androidx.navigation.NavController
import com.example.presentation.R
import timber.log.Timber
import javax.inject.Inject

class HomeNavigationImpl(private val navController: NavController) : HomeNavigationHandler {

    override fun navigateToHome() {
        Timber.d("${navController.currentDestination}")
        Timber.d("${navController.graph}")
        navController.navigate(R.id.mainHomeFragment)
    }

    override fun navigateToMap() {
        Timber.d("${navController.currentDestination}")
        Timber.d("${navController.graph}")
        navController.navigate(R.id.veganMapFragment)
    }

    override fun navigateToTips() {
        Timber.d("${navController.currentDestination}")
        Timber.d("${navController.graph}")
        navController.navigate(R.id.tipsFragment)
    }

    override fun navigateToMypage() {
        Timber.d("${navController.currentDestination}")
        Timber.d("${navController.graph}")
        navController.navigate(R.id.mainMypageFragment)
    }
}