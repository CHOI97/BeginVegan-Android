package com.example.presentation.config.navigation.main

import androidx.navigation.NavController
import com.example.presentation.R

class MainNavigationImpl(private val navController: NavController): MainNavigationHandler {

    override fun navigateToMainHome() {
        navController.navigate(R.id.mainFragment)
    }
}