package com.example.presentation.config.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.presentation.R
import javax.inject.Inject

class MainNavigationImpl @Inject constructor(private val navController: NavController) :
    MainNavigationHandler {

    override fun navigateToHome() {
        val currentId = navController.currentDestination?.id!!
        navController.navigate(R.id.mainHomeFragment, null,
            NavOptions.Builder().setPopUpTo(currentId, true).build())
    }
    override fun navigateToMap() { controlBackStack(R.id.veganMapFragment) }
    override fun navigateToTips() { controlBackStack(R.id.mainTipsFragment) }
    override fun navigateToMypage() { controlBackStack(R.id.mainMypageFragment) }

    override fun navigateHomeToVeganTest() {
        navController.navigate(R.id.action_homeFragment_to_veganTestFragment)
    }
    override fun navigateTestToVeganTestResult() {
        navController.navigate(R.id.action_veganTestFragment_to_veganTestResultFragment)
    }
    override fun navigateTestResultToTips() {
        navController.navigate(R.id.action_veganTestResultFragment_to_tipsFragment)
    }

    override fun navigateHomeToMagazineDetail() {
        navController.navigate(R.id.action_mainHomeFragment_to_tipsMagazineDetailFragment)
    }

    override fun navigateHomeToMyMagazine() {
        navController.navigate(R.id.action_mainHomeFragment_to_mypageMyMagazineFragment)
    }

    override fun navigateTipsToMagazineDetail() {
        navController.navigate(R.id.action_tipsFragment_to_tipsMagazineDetailFragment)
    }

    override fun navigateMypageToEditProfile() {
        navController.navigate(R.id.action_mainMypageFragment_to_mypageEditProfileFragment)
    }

    override fun navigateMypageToMyReview() {
        navController.navigate(R.id.action_mainMypageFragment_to_mypageMyReviewFragment)
    }

    override fun navigateMypageToMyRestaurant() {
        navController.navigate(R.id.action_mainMypageFragment_to_mypageMyRestaurantFragment)
    }

    override fun navigateMypageToMyMagazine() {
        navController.navigate(R.id.action_mainMypageFragment_to_mypageMyMagazineFragment)
    }

    override fun navigateMypageToMyRecipe() {
        navController.navigate(R.id.action_mainMypageFragment_to_mypageMyRecipeFragment)
    }

    override fun navigateMypageToMySetting() {
        navController.navigate(R.id.action_mainMypageFragment_to_mypageSettingFragment)
    }

    override fun navigateMyReviewToMap() {
        navController.navigate(R.id.action_mypageMyReviewFragment_to_veganMapFragment)
    }

    override fun navigateMyRestaurantToMap() {
        navController.navigate(R.id.action_mypageMyRestaurantFragment_to_veganMapFragment)
    }

    override fun navigateMyRecipeToTips() {
        navController.navigate(R.id.action_mypageMyRecipeFragment_to_tipsFragment)
    }

    override fun navigateMyMagazineToTips() {
        navController.navigate(R.id.action_mypageMyMagazineFragment_to_tipsFragment)
    }

    override fun navigateMyMagazineToMagazineDetail() {
        navController.navigate(R.id.action_mypageMyMagazineFragment_to_tipsMagazineDetailFragment)
    }

    override fun navigateTipsMagazineToMyMagazine() {
        navController.navigate(R.id.action_mainTipsFragment_to_mypageMyMagazineFragment)
    }

    override fun navigateTipsMagazineDetailToMyMagazine() {
        navController.navigate(R.id.action_tipsMagazineDetailFragment_to_mypageMyMagazineFragment)
    }

    override fun navigateTipsRecipeToMyRecipe() {
        navController.navigate(R.id.action_mainTipsFragment_to_mypageMyRecipeFragment)
    }

    override fun navController(): NavController {
        return navController
    }

    private fun controlBackStack(destinationId:Int){
        val currentId = navController.currentDestination?.id!!
        if(currentId == R.id.mainHomeFragment){
            navController.navigate(destinationId)
        }else{
            navController.navigate(destinationId, null,
                NavOptions.Builder().setPopUpTo(currentId, true).build())
        }
    }
}