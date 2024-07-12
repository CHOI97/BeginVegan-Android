package com.example.presentation.config.navigation.main

import androidx.navigation.NavArgument
import androidx.navigation.NavController

interface MainNavigationHandler {
    fun navigateToEditProfile()
    fun navigateToMyRestaurant()
    fun navigateToMyMagazine()
    fun navigateToMyRecipe()
    fun navigateToMySetting()
    fun navigateToReview()
    fun navigateToBeganTest()

    fun navigateToBeganTestResult()
    fun navigateToTipsMagazineDetail()
    fun navigationMyMagazineToMagazineDetail()

    fun navigateToMainHome(fromTest:Boolean = false)

    fun popBackStack()

    fun navController():NavController
}