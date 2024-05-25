package com.example.presentation.view.main

import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.notification.Notification
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.config.navigation.home.HomeNavigationHandler
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.util.DrawerController
import com.example.presentation.view.notification.NotificationNewRvAdapter
import com.example.presentation.view.notification.NotificationOldRvAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    @Inject
    lateinit var drawerController: DrawerController

    override fun initViewModel() {
    }
    override fun init() {
        binding.dlDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        setDrawerRv()
    }

    private fun setDrawerRv(){
        binding.includedDrawer.ibBackUp.setOnClickListener {
            drawerController.closeDrawer()
        }

        val newRecyclerView = binding.includedDrawer.rvNewNotification
        val oldRecyclerView = binding.includedDrawer.rvOldNotification

        val list = mutableListOf(
            Notification(1,"VEGAN","TEST111","1/2/3", false),
            Notification(1,"VEGAN","TEST2222","1/2/3", true),
            Notification(1,"VEGAN","TEST3333","1/2/3", false)
        )
        val newRvAdapter = NotificationNewRvAdapter(list, this)
        newRecyclerView.adapter = newRvAdapter
        newRecyclerView.layoutManager = LinearLayoutManager(this)

        val oldRvAdapter = NotificationOldRvAdapter(list, this)
        oldRecyclerView.adapter = oldRvAdapter
        oldRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}