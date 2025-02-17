package com.example.presentation.view.notification.view

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.alarms.Alarm
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentNotificationDrawerBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.DrawerController
import com.example.presentation.view.notification.adapter.NotificationReadRvAdapter
import com.example.presentation.view.notification.adapter.NotificationUnreadRvAdapter
import com.example.presentation.view.notification.viewModel.NotificationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationDrawerFragment:BaseFragment<FragmentNotificationDrawerBinding>(R.layout.fragment_notification_drawer) {
    @Inject
    lateinit var drawerController: DrawerController
    private val notificationViewModel: NotificationViewModel by viewModels()

    override fun init() {
        setDrawerRv()
    }

    private fun setDrawerRv(){
        binding.ibBackUp.setOnClickListener {
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
        val unreadRecyclerView = binding.rvUnreadNotification

        if(list.isEmpty()){
            binding.tvUnreadTitle.isVisible = false
            unreadRecyclerView.isVisible = false
            binding.vDivider.isVisible = false
        }else{
            val newRvAdapter = NotificationUnreadRvAdapter(list,requireContext())
            unreadRecyclerView.adapter = newRvAdapter
            unreadRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun setReadAlarmList(list:List<Alarm>){
        val readRecyclerView = binding.rvReadNotification

        val oldRvAdapter = NotificationReadRvAdapter(list, requireContext())
        readRecyclerView.adapter = oldRvAdapter
        readRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}