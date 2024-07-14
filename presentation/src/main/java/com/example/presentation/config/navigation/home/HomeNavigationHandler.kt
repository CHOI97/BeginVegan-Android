package com.example.presentation.config.navigation.home

import androidx.navigation.NavController

interface HomeNavigationHandler {
    fun navigateToHome()
    fun navigateToMap(fromMyRestaurant:Boolean = false, fromMyReview:Boolean = false)
    fun navigateToTips(fromTest: Boolean = false,fromMyRecipe:Boolean = false)
    fun navigateToMypage()

    fun navigationToMypageNotBackStack()
    fun navigationToTipsNotBackStack()
}