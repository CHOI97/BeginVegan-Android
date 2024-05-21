package com.example.presentation.view.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.NearRestaurant
import com.example.presentation.R
import com.example.presentation.adapter.home.HomeRestaurantRVAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainHomeBinding
import com.example.presentation.config.navigation.TipsNavigationHandler
import com.example.presentation.util.DrawerController
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainHomeFragment: BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home){
    private lateinit var homeRestaurantRVAdapter: HomeRestaurantRVAdapter

    @Inject
    lateinit var drawerController: DrawerController

    @Inject
    lateinit var navigationHandler: TipsNavigationHandler


    // ViewModel 분리
    private var list: ArrayList<NearRestaurant> = ArrayList()

    override fun init() {

//        binding.ivBannerVeganTest.setOnClickListener{
//        }

        setRestaurantRecyclerView()
        setTipsTab()
        setOpenDrawer()
    }
    private fun setOpenDrawer() {
        binding.includedToolbar.ibNotification.setOnClickListener {
            drawerController.openDrawer()
        }
    }

    private fun setTipsTab() {
        binding.tlTips.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> navigationHandler.navigateToMagazine()
                    1 -> navigationHandler.navigateToRecipe()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }



    // ViewModel 분리
    private fun testData(){
        list.add(NearRestaurant(0,"식당1","null"))
        list.add(NearRestaurant(0,"식당2","null"))
        list.add(NearRestaurant(0,"식당3","null"))
        list.add(NearRestaurant(0,"식당4","null"))
        list.add(NearRestaurant(0,"식당5","null"))
        list.add(NearRestaurant(0,"식당6","null"))
        list.add(NearRestaurant(0,"식당7","null"))
    }
    private fun setRestaurantRecyclerView() {
        testData()
        homeRestaurantRVAdapter = HomeRestaurantRVAdapter(requireContext())
        binding.rvRestaurantList.adapter = homeRestaurantRVAdapter
        homeRestaurantRVAdapter.submitList(list.toMutableList())
        binding.rvRestaurantList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

}