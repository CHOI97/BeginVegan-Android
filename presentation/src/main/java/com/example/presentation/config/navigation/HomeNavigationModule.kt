package com.example.presentation.config.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object HomeNavigationModule {

    @Provides
    fun provideNavController(fragment: Fragment): NavController {
        return NavHostFragment.findNavController(fragment)
    }

    @Provides
    fun provideNavigationHandler(navController: NavController): NavigationHandler {
        return HomeNavigationHandler(navController)
    }
}
