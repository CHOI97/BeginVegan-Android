package com.example.presentation.config.navigation.main

import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import com.example.presentation.view.home.veganTest.view.VeganTestResultFragmentDirections
import com.example.presentation.view.mypage.view.MypageMyMagazineFragment
import com.example.presentation.view.mypage.view.MypageMyMagazineFragmentDirections
import com.example.presentation.view.mypage.view.MypageMyRecipeFragmentDirections
import com.example.presentation.view.mypage.view.MypageMyRestaurantFragmentDirections
import timber.log.Timber
import javax.inject.Inject

class MainNavigationImpl @Inject constructor(private val navController: NavController) : MainNavigationHandler {

//    override fun navigateToEditProfile() {
//        navController.navigate(R.id.action_mainFragment_to_mypageEditProfileFragment)
//    }
//
//    override fun navigateToMyRestaurant() {
//        navController.navigate(R.id.action_mainFragment_to_mypageMyRestaurantFragment)
//    }
//
//    override fun navigateToMyMagazine() {
//        navController.navigate(R.id.action_mainFragment_to_mypageMyMagazineFragment)
//    }
//
//    override fun navigateToMyRecipe() {
//        navController.navigate(R.id.action_mainFragment_to_mypageMyRecipeFragment)
//    }
//
//    override fun navigateToMySetting() {
//        navController.navigate(R.id.action_mainFragment_to_mypageSettingFragment)
//    }
//
//    override fun navigateToReview() {
//        navController.navigate(R.id.action_mainFragment_to_mypageMyReviewFragment)
//    }
//
//    override fun navigateToBeganTest() {
//        navController.navigate(R.id.action_mainFragment_to_veganTestFragment)
//    }
//
//    override fun navigateToBeganTestResult() {
//        navController.navigate(R.id.action_veganTestFragment_to_veganTestResultFragment)
//    }
//
//    override fun navigateToTipsMagazineDetail() {
//        navController.navigate(R.id.action_mainFragment_to_tipsMagazineDetailFragment)
//    }

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

    override fun navController(): NavController {
        return navController
    }

//    override fun navigateMyMagazineToMainHome(fromMagazine: Boolean) {
//        val action = MypageMyMagazineFragmentDirections.actionMypageMyMagazineFragmentToMainFragment(fromMyMagazine = fromMagazine)
//        navController.navigate(action)
//    }
//
//    override fun navigateMyRecipeToMainHome(fromMyRecipe: Boolean) {
//        val action = MypageMyRecipeFragmentDirections.actionMypageMyRecipeFragmentToMainFragment(fromMyRecipe = fromMyRecipe)
//        navController.navigate(action)
//    }
//
//    override fun navigateMyRestaurantToMainHome(fromMyRestaurant: Boolean) {
//        val action = MypageMyRestaurantFragmentDirections.actionMypageMyRestaurantFragmentToMainFragment(fromMyRestaurant = fromMyRestaurant)
//        navController.navigate(action)
//    }
//
//    override fun navigateToMainHome(fromTest: Boolean) {
//        val action = VeganTestResultFragmentDirections.actionVeganTestResultFragmentToMainFragment(fromTest = fromTest)
//        navController.navigate(action)
//    }

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