package com.example.presentation.view.main

import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.alarms.Alarm
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.DrawerController
import com.example.presentation.view.notification.adapter.NotificationReadRvAdapter
import com.example.presentation.view.notification.adapter.NotificationUnreadRvAdapter
import com.example.presentation.view.notification.viewModel.NotificationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    @Inject
    lateinit var drawerController: DrawerController
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun initViewModel() {
    }
    override fun init() {
        setDrawer()
    }

    private fun setDrawer(){
        binding.dlDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        setDrawerRv()
    }

    private fun setDrawerRv(){
        binding.includedDrawer.ibBackUp.setOnClickListener {
            drawerController.closeDrawer()
        }

        lifecycleScope.launch {
            notificationViewModel.alarmLists.collect{state->
                when(state){
                    is NetworkResult.Loading ->{

                    }
                    is NetworkResult.Success ->{
                        val unreadList = state.data!!.response!!.unreadAlarmList
                        setUnreadAlarmList(unreadList)
                        val readlist = state.data!!.response!!.readAlarmList
                        setReadAlarmList(readlist)
                    }
                    is NetworkResult.Error ->{

                    }
                }
            }
        }

    }
    private fun setUnreadAlarmList(list:List<Alarm>){
        val unreadRecyclerView = binding.includedDrawer.rvUnreadNotification

        if(list.isEmpty()){
            binding.includedDrawer.tvUnreadTitle.isVisible = false
            unreadRecyclerView.isVisible = false
            binding.includedDrawer.vDivider.isVisible = false
        }else{
            val newRvAdapter = NotificationUnreadRvAdapter(list,this)
            unreadRecyclerView.adapter = newRvAdapter
            unreadRecyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
    private fun setReadAlarmList(list:List<Alarm>){
        val readRecyclerView = binding.includedDrawer.rvReadNotification

        val oldRvAdapter = NotificationReadRvAdapter(list, this)
        readRecyclerView.adapter = oldRvAdapter
        readRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}