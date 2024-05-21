package com.example.presentation.config.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

@Module
@InstallIn(FragmentComponent::class)
object NavigationModule {

    // Main Graph
    @Provides
    @MainGraph
    fun provideMainNavController(fragment: Fragment): NavController {
        return NavHostFragment.findNavController(fragment)
    }

    @Provides
    fun provideMainNavigationHandler(@MainGraph navController: NavController): HomeNavigationHandler {
        return HomeNavigationImpl(navController)
    }

    // Home Graph
    @Provides
    @HomeGraph
    fun provideHomeNavController(fragment: Fragment): NavController {
        return NavHostFragment.findNavController(fragment)
    }

    @Provides
    fun provideHomeNavigationHandler(@HomeGraph navController: NavController): TipsNavigationHandler {
        return TipsNavigationImpl(navController)
    }
    @Provides
    @TipsGraph
    fun provideTipsNavController(fragment: Fragment): NavController {
        return NavHostFragment.findNavController(fragment)
    }

    @Provides
    fun provideTipsNavigationHandler(@TipsGraph navController: NavController): TipsNavigationHandler {
        return TipsNavigationImpl(navController)
    }
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MainGraph

    // Home, Map, Tips, Mypage
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HomeGraph

    // Magazine, Recipe
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TipsGraph
}
