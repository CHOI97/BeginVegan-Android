package com.example.presentation.config.navigation

import androidx.navigation.NavController
import com.example.presentation.R

class HomeNavigationHandler(private val navController: NavController) : NavigationHandler {
    override fun navigateToMagazine() {
        navController.navigate(R.id.homeTipsMagazineFragment)
    }

    override fun navigateToRecipe() {
        navController.navigate(R.id.homeTipsRecipeFragment)
    }
}