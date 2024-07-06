package com.example.presentation.config.drawer

import android.app.Activity
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.presentation.R
import com.example.presentation.util.DrawerController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import timber.log.Timber

@Module
@InstallIn(ActivityComponent::class)
object DrawerControllerModule {
    @Provides
    fun provideDrawerHandler(activity: Activity): DrawerController = object : DrawerController {
        override fun openDrawer() {
            Timber.d("open Drawer")
            val drawerLayout: DrawerLayout = activity.findViewById(R.id.dl_drawer)
            drawerLayout.openDrawer(GravityCompat.END)

//            val preventMissTouch: View = activity.findViewById(R.id.v_prevent_miss_touch)
//            preventMissTouch.visibility = View.VISIBLE
//            preventMissTouch.setOnClickListener {
//                closeDrawer()
//            }
        }
        override fun closeDrawer() {
            val drawerLayout: DrawerLayout = activity.findViewById(R.id.dl_drawer)
            drawerLayout.closeDrawer(GravityCompat.END)
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        }
    }
}