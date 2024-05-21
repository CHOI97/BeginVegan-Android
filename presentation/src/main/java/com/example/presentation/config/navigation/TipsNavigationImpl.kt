package com.example.presentation.config.navigation

import androidx.navigation.NavController
import com.example.presentation.R

class TipsNavigationImpl(private val navController: NavController) : TipsNavigationHandler {
    override fun navigateToMagazine() {
        navController.navigate(R.id.homeTipsMagazineFragment)
    }

    override fun navigateToRecipe() {
        navController.navigate(R.id.homeTipsRecipeFragment)
    }
}