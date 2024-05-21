package com.example.presentation.view.home

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.NearRestaurant
import com.example.presentation.R
import com.example.presentation.adapter.home.HomeRestaurantRVAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainHomeBinding
import com.example.presentation.util.DrawerController
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainHomeFragment: BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home){
    private lateinit var homeRestaurantRVAdapter: HomeRestaurantRVAdapter

    @Inject
    lateinit var drawerController: DrawerController

    private var list: ArrayList<NearRestaurant> = ArrayList()

    override fun init() {

        binding.ivBannerVeganTest.setOnClickListener{
            moveToOtherFragment(VeganTestFragment())
        }

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
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fcw_tips) as NavHostFragment
        val navController = navHostFragment.navController
        binding.tlTips.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(p0: TabLayout.Tab?) {
                when(p0?.position){
                    0 ->{
                        navController.navigate(R.id.homeTipsMagazineFragment)
                    }
                    1->{
                        navController.navigate(R.id.homeTipsRecipeFragment)
                    }
                }
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })
    }

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

    private fun moveToOtherFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcw_main, fragment)
            .addToBackStack(null)
            .commit()
    }


}