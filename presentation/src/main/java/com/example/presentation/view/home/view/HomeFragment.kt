package com.example.presentation.view.home.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.map.VeganMapRestaurant
import com.example.presentation.R
import com.example.presentation.adapter.home.HomeRestaurantRVAdapter
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.FragmentMainHomeBinding
import com.example.presentation.util.DrawerController
import com.example.presentation.util.PermissionDialog
import com.example.presentation.view.main.MainViewModel
import com.example.presentation.view.mypage.view.MypagePushAlertDialog
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.shreyaspatil.permissionFlow.MultiplePermissionState
import dev.shreyaspatil.permissionFlow.PermissionFlow
import dev.shreyaspatil.permissionFlow.utils.registerForPermissionFlowRequestsResult
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentMainHomeBinding>(R.layout.fragment_main_home) {
    private lateinit var homeRestaurantRVAdapter: HomeRestaurantRVAdapter
    private val mainViewModel: MainViewModel by hiltNavGraphViewModels(R.id.nav_main_graph)

    @Inject
    lateinit var drawerController: DrawerController

    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    private var tipsNowTab = "MAGAZINE"

    private var list: ArrayList<VeganMapRestaurant> = ArrayList()

    //권한 요청
    private val permissionFlow = PermissionFlow.getInstance()
    private val permissionLauncher = registerForPermissionFlowRequestsResult()
    private lateinit var permissions:Array<String>
//    private val REQUEST_NOTIFICATION_PERMISSION = 2
    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager

//    private val multiPermissionLauncher =
//        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionList ->
////            val isFineLocation = isGranted[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
////            val isCoarseLocation = isGranted[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
//            if(permissionList.all{it.value}){
//
//            }else{
//
//            }
//            when {
//                isFineLocation && isCoarseLocation -> {
//                    // FineLoaction 승인 시, CoarseLoaction 자동 승인
//                    // 정확한 위치 권한 승인
//                    logMessage("locationPermissionLauncher Fine Location, Coarse Location Granted 정확한 위치 권한 승인")
//                    getLocation()
//                    getFineLocation()
//                }
//
//                !isFineLocation && isCoarseLocation -> {
//                    // 대략적인 위치 권한 승인
//                    logMessage("locationPermissionLauncher Only Coarse Location Granted 대략적인 위치 권한 승인")
//                    getLocation()
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(
//                            requireActivity(),
//                            ACCESS_FINE_LOCATION
//                        )
//                    ) {
//                        logMessage("locationPermissionLauncher Fine Location 거부 경험 있음")
//                        showPermissionDeniedDialog()
//                    } else {
//                        logMessage("locationPermissionLauncher Fine Location 거부 경험 없음")
//                        showFineLocationDialog()
//                    }
//
//                }
//
//                else -> {
//                    // 위치 권한 승인하지 않음
//                    logMessage("locationPermissionLauncher Permission Denied 위치 권한 거부")
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(
//                            requireActivity(),
//                            ACCESS_COARSE_LOCATION
//                        )
//                    ) {
//                        logMessage("locationPermissionLauncher 위치 권한 거부 경험 없음")
//                        showPermissionRationaleDialog()
//                    } else {
//                        logMessage("locationPermissionLauncher 위치 권한 거부 경험 있음")
//                        showPermissionDeniedDialog()
//                    }
//                }
//            }
//        }

    companion object {
        private const val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        private const val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        private const val POST_NOTIFICATIONS = Manifest.permission.POST_NOTIFICATIONS
    }

    override fun init() {
        binding.lifecycleOwner = this

        setUserInfo()

        //View 세팅
        setOnClickListener()
        setTipsTab()
        setRestaurantRecyclerView()

        //권한 요청
        checkApiVersion()
    }

    private fun setUserInfo() {
    }


    private fun setOnClickListener() {
        with(binding){
            ivBannerVeganTest.setOnClickListener {
                mainNavigationHandler.navigateHomeToVeganTest()
            }
            includedToolbar.ibNotification.setOnClickListener {
                drawerController.openDrawer()
            }
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
    private fun setRestaurantRecyclerView() {
        homeRestaurantRVAdapter = HomeRestaurantRVAdapter(requireContext())
        binding.rvRestaurantList.adapter = homeRestaurantRVAdapter
        homeRestaurantRVAdapter.submitList(list.toMutableList())
        binding.rvRestaurantList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getLocation() {
        locationManager = ContextCompat.getSystemService(
            requireContext(),
            LocationManager::class.java
        ) as LocationManager

        val location: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        location?.let {
            val latitude = location.latitude
            val longitude = location.longitude
            val accuracy = location.accuracy
            val time = location.time
            logMessage("getLocation\nlatitude = $latitude,\nlongitude = $longitude\nlocation = $location,\naccuracy = $accuracy,\ntime = $time")
        }
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // 위치 정보가 변경될 때 호출되는 콜백
                logMessage("onLocationChanged")
                logMessage("${location.latitude} ${location.latitude}")
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                // 위치 제공자 상태 변경 시 호출되는 콜백
                logMessage("onStatusChanged")
            }

            override fun onProviderEnabled(provider: String) {
                // 위치 제공자가 사용 가능할 때 호출되는 콜백
                logMessage("onProviderEnabled")
            }

            override fun onProviderDisabled(provider: String) {
                // 위치 제공자가 사용 불가능할 때 호출되는 콜백
                logMessage("onProviderDisabled")
            }
        }
    }

    private fun getFineLocation() {
//        try {
//            logMessage("getFineLocation granted")
//            locationManager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER,
//                5000L, // 5초
//                10f, // 10미터,
//                locationListener
//            )
//        } catch (e: SecurityException) {
//            logMessage("Location permission not granted")
//            showPermissionDeniedDialog()
//        }
    }

    /**
     * 권한 요청
     */
    private fun checkApiVersion(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){ //API 33이상
            permissions = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, POST_NOTIFICATIONS)
        }else{
            permissions = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)
        }
        multiplePermissions()
    }
    private fun multiplePermissions(){
        permissionLauncher.launch(permissions)
        viewLifecycleOwner.lifecycleScope.launch {
            permissionFlow.getMultiplePermissionState(*permissions)
                .flowWithLifecycle(lifecycle)
                .onEach { onPermissionStateAction(it) }
                .launchIn(lifecycleScope)
        }
    }

    /**
     * 여기서 권한 허용 여부에 따른 분기 처리
     */
    private fun onPermissionStateAction(state:MultiplePermissionState) {
        when {
            ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        requireContext(),
                        ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED -> {
                logMessage("checkAndRequestPermissions 정확한 위치 권한 승인")
                getLocation()
            }

            ActivityCompat.checkSelfPermission(
                requireContext(),
                ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                logMessage("checkAndRequestPermissions 대략적인 위치 권한 승인")
                getLocation()
            }

            else -> {
                val deniedPermissions = state.deniedPermissions

                if (deniedPermissions.isNotEmpty()) {
                    deniedPermissions.forEach { permission ->
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                requireActivity(),
                                permission
                            )
                        ) {
                            logMessage("권한 $permission 거부됨, 다시 요청 가능")
                            when(permission){
                                ACCESS_FINE_LOCATION -> {
                                    showPermissionRationaleDialog(
                                        "해당 권한을 거부할 경우, 다음 기능의 사용이 불가능해요." + "\n · Map 기능 전체 "
                                    )
                                }
                                ACCESS_COARSE_LOCATION -> showPermissionDeniedDialog()
                                POST_NOTIFICATIONS -> {
                                    logMessage("POST_NOTIFICATION 거절 후 함수 실행")
                                    MypagePushAlertDialog(permit = false, mypage = false)
                                        .show(childFragmentManager, "RefusePushDialog")
                                }
                            }
                        } else {
                            logMessage("권한 $permission 거부됨, '다시 묻지 않기' 선택됨")
//                            if(permission == ACCESS_COARSE_LOCATION) showPermissionDeniedDialog()
                        }
                    }
                }
            }
        }
    }
//    private fun checkAndRequestPermissions() {
//        when {
//            ActivityCompat.checkSelfPermission(requireContext(), ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                    ContextCompat.checkSelfPermission(requireContext(), ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
//                    logMessage("checkAndRequestPermissions 정확한 위치 권한 승인")
//                    getLocation()
//            }
//
//            ActivityCompat.checkSelfPermission(requireContext(), ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
//                logMessage("checkAndRequestPermissions 대략적인 위치 권한 승인")
//                getLocation()
//            }
//
//            else -> {
//                logMessage("checkAndRequestPermissions 위치 권한 없음")
//                if (ActivityCompat.shouldShowRequestPermissionRationale(
//                        requireActivity(),
//                        ACCESS_COARSE_LOCATION
//                    )
//                ) {
//                    logMessage(
//                        "shouldShowRequestPermissionRationale = true"
//                    )
//                } else {
//                    logMessage(
//                        "shouldShowRequestPermissionRationale = false"
//                    )
////                    locationPermissionLauncher.launch(permissions)
//                }
//
//            }
//        }
//    }

    //     권한 재요청
    private fun showPermissionRationaleDialog(body:String) {
        var isRetry = false
        PermissionDialog.Builder()
            .setTitle("권한 재요청 안내")
            .setBody(body)
            .setPositiveButton("권한재요청") {
                isRetry = true
//                permissionLauncher.launch(arrayOf(ACCESS_FINE_LOCATION))
                viewLifecycleOwner.lifecycleScope.launch {
                    permissionFlow.getMultiplePermissionState(ACCESS_FINE_LOCATION)
                        .flowWithLifecycle(lifecycle)
                        .onEach { permissionState ->
                            onPermissionStateAction(permissionState) // 권한 상태 처리
                        }
                        .collect{}
                }
//                multiplePermissions()
            }.setNegativeButton("닫기") {
                logMessage("닫기")
            }
            .setOnDismissListener {
                if (!isRetry) {
                    showPermissionDeniedDialog()
                }
            }
            .show(childFragmentManager, "showPermissionRationaleDialog")
    }

    //
    // 권한 허용 안함
    private fun showPermissionDeniedDialog() {
        PermissionDialog.Builder()
            .setTitle("기능 사용 불가 안내")
            .setBody(
                "위치 정보에 대한 권한 사용을 거부하셨어요.\n" +
                        "\n" +
                        "기능 사용을 원하실 경우 [휴대폰 설정 > 애플리케이션 관리자]에서 해당 앱의 권한을 허용해 주세요."
            )
            .setPositiveButton("확인") {
                logMessage("showPermissionDeniedDialog 확인")
            }.show(childFragmentManager, "showPermissionDeniedDialog")
    }

//    private fun showFineLocationDialog() {
//        val dialog = PermissionDialog.Builder()
//            .setTitle("정확한 위치 권한 요청 안내")
//            .setBody(
//                "Map 메뉴는 '정확한 위치' 권한으로만 사용 가능합니다.\n" +
//                        "'정확한 위치' 사용 권한을 허용해 주세요."
//            )
//            .setPositiveButton("설정") {
//                multiplePermissions()
//            }.setNegativeButton("닫기") {
//                showPermissionDeniedDialog()
//                logMessage("showFineLocationDialog 닫기")
//            }.show(childFragmentManager, "showFineLocationDialog")
//    }
//
//    //알림 권한 설정
//    private fun requestNotificationPermission1() {
//        // Android 13 이상일 경우에만 알림 권한 요청
//        Timber.d("requestNotificationPermission 알림 권한 요청 실행 ")
//        Timber.d("Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU: ${Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU}")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)
//                == PackageManager.PERMISSION_GRANTED
//            ) {
//                // 이미 권한이 부여된 경우
//                // 권한이 이미 부여된 경우 처리할 로직
//                Timber.d("이미 권한이 부여된 경우")
//            } else if (ActivityCompat.shouldShowRequestPermissionRationale(
//                    requireActivity(),
//                    Manifest.permission.POST_NOTIFICATIONS
//                )
//            ) {
//                // 권한 요청의 필요성을 설명하는 다이얼로그를 표시
//                Timber.d("권한 요청의 필요성을 설명하는 다이얼로그를 표시")
//                showPermissionRationale()
//            } else {
//                // 권한 요청
//                Timber.d("권한 요청")
//                ActivityCompat.requestPermissions(
//                    requireActivity(),
//                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
//                    REQUEST_NOTIFICATION_PERMISSION
//                )
//            }
//        }
//    }
//    private fun showPermissionRationale() {
//        PermissionDialog.Builder()
//            .setTitle("알림 권한 요청")
//            .setBody("'비긴, 비건'에서 알림을 보내도록 허용하시겠습니까?")
//            .setPositiveButton("허용") {
//                ActivityCompat.requestPermissions(
//                    requireActivity(),
//                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
//                    REQUEST_NOTIFICATION_PERMISSION
//                )
//            }.setNegativeButton("허용 안함") {
//                MypagePushAlertDialog(permit = false, mypage = false).show(childFragmentManager, "RefusePushDialog")
//            }.show(childFragmentManager, "RefusePushDialog")
//    }
}