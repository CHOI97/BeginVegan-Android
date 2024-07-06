package com.example.presentation.view.main

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.alarms.Alarm
import com.example.presentation.R
import com.example.presentation.auth.User
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.DrawerController
import com.example.presentation.view.notification.adapter.NotificationReadRvAdapter
import com.example.presentation.view.notification.adapter.NotificationUnreadRvAdapter
import com.example.presentation.view.notification.view.NotificationDrawerFragment
import com.example.presentation.view.notification.viewModel.NotificationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    @Inject
    lateinit var drawerController: DrawerController
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun initViewModel() {
    }
    override fun init() {
        Timber.d("Main Activity init")
        setDrawer()
        backButton()
    }

    private fun setDrawer(){
        binding.dlDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        setDrawerRv()
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcw_drawer_container) as NavHostFragment
//        val navController = navHostFragment.findNavController()
//        navController.navigate(R.id.notificationDrawerFragment)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.fl_drawer_container,NotificationDrawerFragment())
//            .commit()
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

    //back button 동작 제어
    private fun backButton(){
        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                Timber.d("isDrawerOpen: ${binding.dlDrawer.isDrawerOpen(GravityCompat.END)}")
                if(binding.dlDrawer.isDrawerOpen(GravityCompat.END)){
                    drawerController.closeDrawer()
                }else{
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })
    }
}