package com.example.presentation.config.navigation

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.config.navigation.home.HomeNavigationHandler
import com.example.presentation.config.navigation.home.HomeNavigationImpl
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.config.navigation.main.MainNavigationImpl
import com.example.presentation.config.navigation.tips.TipsNavigationHandler
import com.example.presentation.config.navigation.tips.TipsNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import timber.log.Timber
import javax.inject.Qualifier

@Module
@InstallIn(FragmentComponent::class)
object FragmentNavModule {


    @Provides
    @HomeGraph
    fun provideHomeNavController(fragment: Fragment): NavController {
        val navController = NavHostFragment.findNavController(fragment)
        Timber.d("Providing NavController for Home Graph: $navController , ${NavHostFragment.KEY_GRAPH_ID}")
        return navController
    }

    @Provides
    fun provideHomeNavigationHandler(@HomeGraph navController: NavController): HomeNavigationHandler {
        return HomeNavigationImpl(navController)
    }

    @Provides
    @TipsGraph
    fun provideTipsNavController(fragment: Fragment): NavController {
        val navController = NavHostFragment.findNavController(fragment)
        Timber.d("Providing NavController for Tips Graph: $navController  , ${NavHostFragment.KEY_GRAPH_ID}")
        return navController
    }

    @Provides
    fun provideTipsNavigationHandler(@TipsGraph navController: NavController): TipsNavigationHandler {
        return TipsNavigationImpl(navController)
    }

    // Home, Map, Tips, Mypage
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HomeGraph

    // Magazine, Recipe
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TipsGraph
}
