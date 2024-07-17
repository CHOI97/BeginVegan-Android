package com.example.presentation.view.home.view

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.NearRestaurant
import com.example.presentation.R
import com.example.presentation.adapter.home.HomeRestaurantRVAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.home.HomeNavigationHandler
import com.example.presentation.config.navigation.home.HomeNavigationImpl
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentMainHomeBinding
import com.example.presentation.util.DrawerController
import com.example.presentation.view.home.viewModel.HomeTipsViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home){
    private lateinit var homeRestaurantRVAdapter: HomeRestaurantRVAdapter
    private lateinit var homeNavigationHandler: HomeNavigationHandler

    @Inject
    lateinit var drawerController: DrawerController

    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    private var tipsNowTab = "MAGAZINE"
//    private lateinit var tipsNavigationHandler: TipsNavigationHandler

    // ViewModel 분리
    private var list: ArrayList<NearRestaurant> = ArrayList()

    override fun init() {
        homeNavigationHandler = HomeNavigationImpl(findNavController())
        setRestaurantRecyclerView()
        setTipsTab()
        setOpenDrawer()
        setBeganTest()
    }

    private fun setBeganTest() {
        binding.ivBannerVeganTest.setOnClickListener{
            mainNavigationHandler.navigateToBeganTest()
        }
    }

    private fun setOpenDrawer() {
        binding.includedToolbar.ibNotification.setOnClickListener {
            drawerController.openDrawer()
        }
    }

    private fun setTipsTab() {
//        val navHostFragment = childFragmentManager.findFragmentById(R.id.fcw_tips) as NavHostFragment
//        val navController = navHostFragment.findNavController()
//        tipsNavigationHandler = TipsNavigationImpl(navController)
        replaceFragment(HomeTipsMagazineFragment())
        tipsNowTab = "MAGAZINE"

        binding.tlTips.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        replaceFragment(HomeTipsMagazineFragment())
                        tipsNowTab = "MAGAZINE"
                    }
                    1 -> {
                        replaceFragment(HomeTipsRecipeFragment())
                        tipsNowTab = "RECIPE"
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        setTipsMoreButton()
    }
    private fun setTipsMoreButton(){
        binding.btnTipsMore.setOnClickListener {
            when(tipsNowTab){
                "MAGAZINE" -> {
                    homeNavigationHandler.navigateToTips(fromMyRecipe = false)
                }
                "RECIPE" -> {
                    homeNavigationHandler.navigateToTips(fromMyRecipe = true)
                }
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_tips_content, fragment)
            .commit()
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