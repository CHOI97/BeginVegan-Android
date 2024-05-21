package com.example.presentation.config.navigation

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.presentation.R
import com.example.presentation.config.navigation.home.HomeNavigationHandler
import com.example.presentation.config.navigation.home.HomeNavigationImpl
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.config.navigation.main.MainNavigationImpl
import com.example.presentation.view.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Qualifier

@Module
@InstallIn(ActivityComponent::class)
object ActivityNavModule {

    @Provides
    @MainGraph
    @ActivityScoped
    fun provideNavController(activity: Activity): NavController {
        val navHostFragment = (activity as FragmentActivity).supportFragmentManager
            .findFragmentById(R.id.fcw_main_container) as NavHostFragment
        return navHostFragment.navController
    }


    @Provides
    fun provideMainNavigationHandler(@MainGraph navController: NavController): MainNavigationHandler {
        return MainNavigationImpl(navController)
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MainGraph
}