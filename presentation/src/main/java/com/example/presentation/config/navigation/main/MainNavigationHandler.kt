package com.example.presentation.config.navigation.main

interface MainNavigationHandler {
    fun navigateToEditProfile()
    fun navigateToMyRestaurant()
    fun navigateToMyMagazine()
    fun navigateToMyRecipe()
    fun navigateToMySetting()
    fun navigateToReview()
    fun navigateToBeganTest()

    fun navigateToBeganTestResult()

    fun navigateToMainHome()

    fun popBackStack()
}