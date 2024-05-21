package com.example.presentation.config.drawer

import android.app.Activity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.presentation.R
import com.example.presentation.util.DrawerController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DrawerModule {
    @Provides
    fun provideDrawerHandler(activity: Activity): DrawerController = object : DrawerController {
        override fun openDrawer() {
            val drawerLayout: DrawerLayout = activity.findViewById(R.id.dl_drawer)
            drawerLayout.openDrawer(GravityCompat.END)
        }

        override fun closeDrawer() {
            val drawerLayout: DrawerLayout = activity.findViewById(R.id.dl_drawer)
            drawerLayout.closeDrawer(GravityCompat.END)
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

    }
}