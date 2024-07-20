package com.example.presentation.view.home.view

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.NearRestaurant
import com.example.presentation.R
import com.example.presentation.adapter.home.HomeRestaurantRVAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.FragmentMainHomeBinding
import com.example.presentation.util.DrawerController
import com.example.presentation.view.main.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {
    private lateinit var homeRestaurantRVAdapter: HomeRestaurantRVAdapter
    private val mainViewModel: MainViewModel by navGraphViewModels(R.id.nav_main_graph)

    @Inject
    lateinit var drawerController: DrawerController

    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    private var tipsNowTab = "MAGAZINE"

    private var list: ArrayList<NearRestaurant> = ArrayList()

    private val fineLocationPermission = Manifest.permission.ACCESS_FINE_LOCATION
    private val coarseLocationPermission = Manifest.permission.ACCESS_COARSE_LOCATION
    private val permissions = arrayOf(fineLocationPermission, coarseLocationPermission)

    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                startLocationUpdates()
            } else {
                logMessage("Location permission denied")
            }
        }


    override fun init() {
        binding.lifecycleOwner = this

        setRestaurantRecyclerView()
        setTipsTab()
        setOpenDrawer()
        setBeganTest()

        locationManager = ContextCompat.getSystemService(
            requireContext(),
            LocationManager::class.java
        ) as LocationManager

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // Handle location updates
                logMessage("Location: ${location.latitude}, ${location.longitude}")
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        checkAndRequestPermissions()
    }

    private fun setBeganTest() {
        binding.ivBannerVeganTest.setOnClickListener {
            mainNavigationHandler.navigateHomeToVeganTest()
        }
    }

    private fun setOpenDrawer() {
        binding.includedToolbar.ibNotification.setOnClickListener {
            drawerController.openDrawer()
        }
    }

    private fun setTipsTab() {
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

    private fun setTipsMoreButton() {
        binding.btnTipsMore.setOnClickListener {
            when (tipsNowTab) {
                "MAGAZINE" -> {
                    mainNavigationHandler.navigateToTips()
                }

                "RECIPE" -> {
                    mainViewModel.setTipsMoveToRecipe(true)
                    mainNavigationHandler.navigateToTips()
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
    private fun testData() {
        list.add(NearRestaurant(0, "식당1", "null"))
        list.add(NearRestaurant(0, "식당2", "null"))
        list.add(NearRestaurant(0, "식당3", "null"))
        list.add(NearRestaurant(0, "식당4", "null"))
        list.add(NearRestaurant(0, "식당5", "null"))
        list.add(NearRestaurant(0, "식당6", "null"))
        list.add(NearRestaurant(0, "식당7", "null"))
    }

    private fun setRestaurantRecyclerView() {
        testData()
        homeRestaurantRVAdapter = HomeRestaurantRVAdapter(requireContext())
        binding.rvRestaurantList.adapter = homeRestaurantRVAdapter
        homeRestaurantRVAdapter.submitList(list.toMutableList())
        binding.rvRestaurantList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun checkAndRequestPermissions() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                fineLocationPermission
            ) == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(
                        requireContext(),
                        coarseLocationPermission
                    ) == PackageManager.PERMISSION_GRANTED -> {
                startLocationUpdates()
            }

            else -> {
                locationPermissionLauncher.launch(permissions)
            }
        }
    }

    private fun startLocationUpdates() {
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000L, // 5초
                10f, // 10미터
                locationListener
            )
        } catch (e: SecurityException) {
            logMessage("Location permission not granted")
        }
    }

    //     권한 재요청
    private fun showPermissionRationaleDialog(context: Context) {
        var isRetry = false
        val dialog = AlertDialog.Builder(context)
            .setTitle("권한 재요청 안내")
            .setMessage(
                "해당 권한을 거부할 경우, 다음 기능의 사용이 불가능해요." +
                        "\n· Map 리뷰 작성 시, 이미지 등록 " +
                        "\n· Mypage 프로필 이미지 등록"
            )
            .setPositiveButton("권한재요청") { _, _ ->
                isRetry = true
                locationPermissionLauncher.launch(permissions)
            }
            .setNegativeButton("닫기") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
        dialog.setOnDismissListener {
            if (!isRetry) {
                showPermissionDeniedDialog(context)
            }
        }
    }

    // 권한 허용 안함
    private fun showPermissionDeniedDialog(context: Context) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("기능 사용 불가 안내")
            .setMessage(
                "카메라 사용에 대한 권한 사용을 거부하셨어요. \n" +
                        "\n" +
                        "기능 사용을 원하실 경우 ‘휴대폰 설정 > 애플리케이션 관리자’에서 해당 앱의 권한을 허용해 주세요."
            )
            .setNegativeButton("확인") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        locationManager.removeUpdates(locationListener)
    }


}