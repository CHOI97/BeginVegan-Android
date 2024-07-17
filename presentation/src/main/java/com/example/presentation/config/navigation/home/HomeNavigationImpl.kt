package com.example.presentation.config.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.presentation.R
import com.example.presentation.view.home.view.HomeFragmentDirections

class HomeNavigationImpl(private val navController: NavController) : HomeNavigationHandler {

    override fun navigateToHome() {
        val currentId = navController.currentDestination?.id!!
        navController.navigate(R.id.mainHomeFragment, null,
            NavOptions.Builder().setPopUpTo(currentId, true).build())
    }

    override fun navigateToMap(fromMyRestaurant: Boolean, fromMyReview: Boolean) {
        if(fromMyRestaurant||fromMyReview){
            val action = HomeFragmentDirections.actionMainHomeFragmentToVeganMapFragment(
                fromMyRestaurant = fromMyRestaurant, fromMyReview = fromMyReview)
            navController.navigate(action)
        }else{
            controlBackStack(R.id.veganMapBaseFragment)
        }
    }

    override fun navigateToTips(fromTest: Boolean, fromMyRecipe:Boolean) {
        if(fromTest||fromMyRecipe){
            val action = HomeFragmentDirections.actionMainHomeFragmentToTipsFragment(fromTest=fromTest, fromMyRecipe = fromMyRecipe)
            navController.navigate(action)
        }else{
            controlBackStack(R.id.tipsFragment)
        }
    }

    override fun navigateToMypage() {
        controlBackStack(R.id.mainMypageFragment)
    }

    override fun navigationToMypageNotBackStack() {
        navController.navigate(R.id.mainMypageFragment, null,
            NavOptions.Builder().setPopUpTo(navController.currentDestination?.id!!, true).build())
    }

    override fun navigationToTipsNotBackStack() {
        navController.navigate(R.id.tipsFragment, null,
            NavOptions.Builder().setPopUpTo(navController.currentDestination?.id!!, true).build())
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