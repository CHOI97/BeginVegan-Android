package com.example.presentation.view.map.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.map.VeganMapRestaurant
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainMapBinding
import com.example.presentation.util.PermissionDialog
import com.example.presentation.util.RestaurantReportDialog
import com.example.presentation.view.map.adapter.VeganMapRestaurantRVAdapter
import com.example.presentation.view.map.viewModel.VeganMapViewModel
import com.example.presentation.view.mypage.view.MypageMyRestaurantFragmentDirections
import com.example.presentation.view.restaurant.view.RestaurantDetailFragmentArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VeganMapFragment : BaseFragment<FragmentMainMapBinding>(R.layout.fragment_main_map) {
    private lateinit var mapView: MapView

    private val viewModel: VeganMapViewModel by viewModels()

    private lateinit var veganMapRestaurantRVAdapter: VeganMapRestaurantRVAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val permissions = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)

    private lateinit var locationListener: LocationListener
    private lateinit var locationManager: LocationManager

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            val isFineLocation = isGranted[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
            val isCoarseLocation = isGranted[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

            when {
                isFineLocation && isCoarseLocation -> {
                    // FineLoaction 승인 시, CoarseLoaction 자동 승인
                    // 정확한 위치 권한 승인
                    logMessage("locationPermissionLauncher Fine Location, Coarse Location Granted 정확한 위치 권한 승인")
                    getLocation()
                }

                !isFineLocation && isCoarseLocation -> {
                    // 대략적인 위치 권한 승인
                    logMessage("locationPermissionLauncher Only Coarse Location Granted 대략적인 위치 권한 승인")
                    getLocation()
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(),
                            ACCESS_FINE_LOCATION
                        )
                    ) {
                        logMessage("locationPermissionLauncher Fine Location 거부 경험 있음")
                        showPermissionDeniedDialog()
                    } else {
                        logMessage("locationPermissionLauncher Fine Location 거부 경험 없음")
                        showFineLocationDialog()
                    }

                }

                else -> {
                    // 위치 권한 승인하지 않음
                    logMessage("locationPermissionLauncher Permission Denied 위치 권한 거부")
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(),
                            ACCESS_COARSE_LOCATION
                        )
                    ) {
                        logMessage("locationPermissionLauncher 위치 권한 거부 경험 없음")
                        showPermissionRationaleDialog()
                    } else {
                        logMessage("locationPermissionLauncher 위치 권한 거부 경험 있음")
                        showPermissionDeniedDialog()
                    }
                }
            }


        }

    override fun init() {

        // 권한 체크
        checkAndRequestPermissions()

        // Floating button layer
        setFloatingLayer()
        // MapView
        initMap()

        // BottomSheet Recyclerview
        setRVAdapter()

        //뒤로가기
        setBackUp()

        // 제보하기 버튼
        reportRestaurant()

        setBottomSheet()


    }

    // 40%대에서 floating 멈춤
    private fun setFloatingLayer() {
        val height = getBottomSheetDialogDefaultHeight(60)
        val layoutParams = binding.clCollapse.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.height = height
        binding.clCollapse.layoutParams = layoutParams
    }

    private fun setBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(binding.includedBottomSheet.clBottomSheet)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, state: Int) {
                when (state) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        logMessage("BottomSheetBehavior 접힘")
                    } // 접힘
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        logMessage("BottomSheetBehavior 펼쳐짐")
                    } // 펼쳐짐
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        logMessage("BottomSheetBehavior 숨겨짐")
                    }    // 숨겨짐
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        logMessage("BottomSheetBehavior 절반 펼쳐짐")
                    } // 절반 펼쳐짐
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        logMessage("BottomSheetBehavior 드래그하는 중")
                    }  // 드래그하는 중
                    BottomSheetBehavior.STATE_SETTLING -> {
                        logMessage("BottomSheetBehavior 안정화 단계")
                    }  // 안정화 단계
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                logMessage("slideOffset $slideOffset")
                // 비율에 따라 상태를 조정합니다.
            }

        })
    }

    fun setBottomSheetState(behavior: BottomSheetBehavior<*>, targetState: Int) {
        val bottomSheet = binding.includedBottomSheet.clBottomSheet
        bottomSheet.post {
            when (targetState) {
                1 -> behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                2 -> {
                    // 중간 상태 1로 설정 (비율 0.3)
                    behavior.peekHeight = (bottomSheet.height * 0.3).toInt()
                    behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                }

                3 -> {
                    // 중간 상태 2로 설정 (비율 0.7)
                    behavior.peekHeight = (bottomSheet.height * 0.7).toInt()
                    behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                }

                4 -> {
                    behavior.peekHeight = (bottomSheet.height * 0.9).toInt()
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }
    }

    private fun setRVAdapter() {
        veganMapRestaurantRVAdapter = VeganMapRestaurantRVAdapter()
        binding.includedBottomSheet.rvBottomSheetRestaurantList.adapter =
            veganMapRestaurantRVAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.restaurantList.collect { restaurantList ->
                logMessage("viewLifecycleOwner collect restaurantList $restaurantList")
                veganMapRestaurantRVAdapter.submitList(restaurantList)
            }
        }
        veganMapRestaurantRVAdapter.setOnItemClickListener(object :
            VeganMapRestaurantRVAdapter.OnItemClickListener {
            override fun onClick(data: VeganMapRestaurant) {
                logMessage("VeganMap onClick: $data")
                showToast("${data.name}")
                val action = VeganMapFragmentDirections.actionVeganMapFragmentToRestaurantDetailFragment(restaurantId = data.id, latitude = data.latitude, longitude = data.longitude, imgUrl = data.thumbnail)
                findNavController().navigate(action)
            }

        })
    }


    private fun reportRestaurant() {
        binding.fabMapReport.setOnClickListener {
            RestaurantReportDialog().show(childFragmentManager, "RestaurantReportDialog")
        }
    }

    private fun findCurrentLocation() {
        binding.fabCurrentLocation.setOnClickListener {
            showToast("현재 위치 찾기")
        }
    }

    private fun initMap() {
        mapView = MapView(requireContext())
        binding.mapView.addView(mapView)
        mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                // 지도 API 가 정상적으로 종료될 때 호출됨
            }

            override fun onMapError(error: Exception) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
            }
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaoMap: KakaoMap) {
                // 인증 후 API 가 정상적으로 실행될 때 호출됨
                val layer = kakaoMap.labelManager?.layer
                val centerLabel =
                    layer?.addLabel(LabelOptions.from("centerLabel", position))?.setStyles(
                        LabelStyle.from(R.drawable.ic_red_dot).setAnchorPoint(0.5f, 1.0f)
                    )
                val trackingManager = kakaoMap.trackingManager
                trackingManager?.setTrackingRotation(true)

            }

            override fun getPosition(): LatLng {
                return super.getPosition()
            }

            // Default Zoom Level 15
            override fun getZoomLevel(): Int {
                return 15
            }
        })
    }

    private fun getLocation() {
        logMessage("getLocation")
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
            viewModel.fetchNearRestaurantMap(0, latitude, longitude)
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

    // BottomSheet Sizing | Height 70%
    private fun getBottomSheetDialogDefaultHeight(per: Int): Int {
        return getWindowHeight() * per / 100
        // 위 수치는 기기 높이 대비 70%로 높이를 설정
    }

    private fun getWindowHeight(): Int {
        val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
            windowMetrics.bounds.height() - insets.bottom - insets.top
        } else {
            val displayMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.heightPixels
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


    private fun checkAndRequestPermissions() {
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
                logMessage("checkAndRequestPermissions 위치 권한 없음")
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        requireActivity(),
                        ACCESS_COARSE_LOCATION
                    )
                ) {
                    logMessage(
                        "shouldShowRequestPermissionRationale = true"
                    )
                } else {
                    logMessage(
                        "shouldShowRequestPermissionRationale = false"
                    )
                    locationPermissionLauncher.launch(permissions)
                }

            }
        }
    }

    //     권한 재요청
    private fun showPermissionRationaleDialog() {
        var isRetry = false
        PermissionDialog.Builder()
            .setTitle("권한 재요청 안내")
            .setBody(
                "해당 권한을 거부할 경우, 다음 기능의 사용이 불가능해요." +
                        "\n · Map 기능 전체 "
            )
            .setPositiveButton("권한재요청") {
                isRetry = true
                locationPermissionLauncher.launch(permissions)
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

    private fun showFineLocationDialog() {
        val dialog = PermissionDialog.Builder()
            .setTitle("정확한 위치 권한 요청 안내")
            .setBody(
                "Map 메뉴는 '정확한 위치' 권한으로만 사용 가능합니다.\n" +
                        "'정확한 위치' 사용 권한을 허용해 주세요."
            )
            .setPositiveButton("설정") {
                locationPermissionLauncher.launch(permissions)
            }.setNegativeButton("닫기") {
                showPermissionDeniedDialog()
                logMessage("showFineLocationDialog 닫기")
            }.show(childFragmentManager, "showFineLocationDialog")
    }

    companion object {
        const val SEARCH_DEFAULT = 0
        const val SEARCH_RESULT = 1
        private const val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        private const val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    }

    private fun setBackUp() {
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_veganMapFragment_to_veganMapSearchFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        locationManager.removeUpdates(locationListener)
    }
}
