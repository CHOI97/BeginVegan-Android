package com.example.presentation.config.navigation.tips

import androidx.navigation.NavController
import com.example.presentation.R

class TipsNavigationImpl(private val navController: NavController) : TipsNavigationHandler {
    override fun navigateToMagazine() {
        navController.navigate(R.id.action_homeTipsMagazineFragment_to_homeTipsRecipeFragment)
    }

    override fun navigateToRecipe() {
        navController.navigate(R.id.action_homeTipsRecipeFragment_to_homeTipsMagazineFragment)
    }
}