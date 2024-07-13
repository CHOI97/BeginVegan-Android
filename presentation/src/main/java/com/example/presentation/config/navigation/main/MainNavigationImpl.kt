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
import timber.log.Timber
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

    override fun navigateToTipsMagazineDetail() {
        navController.navigate(R.id.action_mainFragment_to_tipsMagazineDetailFragment)
    }

    override fun navigateMyMagazineToMagazineDetail() {
        navController.navigate(R.id.action_mypageMyMagazineFragment_to_tipsMagazineDetailFragment)
    }

    override fun navigateMyMagazineToMainHome(fromMagazine: Boolean) {
        val action = MypageMyMagazineFragmentDirections.actionMypageMyMagazineFragmentToMainFragment(fromMyMagazine = fromMagazine)
        navController.navigate(action)
    }

    override fun navigateMyRecipeToMainHome(fromMyRecipe: Boolean) {
        val action = MypageMyRecipeFragmentDirections.actionMypageMyRecipeFragmentToMainFragment(fromMyRecipe = fromMyRecipe)
        navController.navigate(action)
    }

    override fun navigateToMainHome(fromTest: Boolean) {
        val action = VeganTestResultFragmentDirections.actionVeganTestResultFragmentToMainFragment(fromTest = fromTest)
        navController.navigate(action)
    }

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun navController():NavController {
        return navController
    }
}