package com.example.presentation.config.navigation.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import javax.inject.Inject

class MainNavigationImpl @Inject constructor(private val navController: NavController) : MainNavigationHandler {

    override fun navigateToEditProfile() {
        navController.navigate(R.id.action_mainFragment_to_mypageEditProfileFragment)
    }

    override fun navigateToMyRestaurant() {
        navController.navigate(R.id.action_mainFragment_to_mypageMyRestaurantFragment)
    }

    override fun navigateToMyMagazine() {
        navController.navigate(R.id.action_mainFragment_to_mypageMyMagazineFragment)
    }

    override fun navigateToMyRecipe() {
        navController.navigate(R.id.action_mainFragment_to_mypageMyRecipeFragment)
    }

    override fun navigateToMySetting() {
        navController.navigate(R.id.action_mainFragment_to_mypageSettingFragment)
    }

    override fun navigateToReview() {
        navController.navigate(R.id.action_mainFragment_to_mypageMyReviewFragment)
    }

    override fun navigateToBeganTest() {
        navController.navigate(R.id.action_mainFragment_to_veganTestFragment)
    }

    override fun navigateToBeganTestResult() {
        navController.navigate(R.id.action_veganTestFragment_to_veganTestResultFragment)
    }

    override fun navigateToMainHome() {
        navController.navigate(R.id.action_veganTestResultFragment_to_mainFragment)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }


}