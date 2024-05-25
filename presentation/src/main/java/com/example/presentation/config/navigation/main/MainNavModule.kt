package com.example.presentation.config.navigation.main

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainNavModule {

    @Provides
    fun provideNavController(activity: FragmentActivity): NavController {
        val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.fcw_main_container) as NavHostFragment
        return navHostFragment.navController
    }

    @Provides
    fun provideMainNavigationHandler(navController: NavController): MainNavigationHandler {
        return MainNavigationImpl(navController)
    }
}