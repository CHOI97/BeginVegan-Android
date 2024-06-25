package com.example.presentation.view.main

import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.alarms.Alarm
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.util.DrawerController
import com.example.presentation.view.notification.NotificationReadRvAdapter
import com.example.presentation.view.notification.NotificationUnreadRvAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.util.Date
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
            Alarm(1,"VEGAN","TEST111",1, Date.from(Instant.now())),
            Alarm(1,"VEGAN","TEST2222",2, Date.from(Instant.now())),
            Alarm(1,"VEGAN","TEST3333",3, Date.from(Instant.now()))
        )
        val newRvAdapter = NotificationUnreadRvAdapter(list, this)
        newRecyclerView.adapter = newRvAdapter
        newRecyclerView.layoutManager = LinearLayoutManager(this)

        val oldRvAdapter = NotificationReadRvAdapter(list, this)
        oldRecyclerView.adapter = oldRvAdapter
        oldRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}