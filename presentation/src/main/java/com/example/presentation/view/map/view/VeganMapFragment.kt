package com.example.presentation.view.map.view

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainMapBinding
import com.example.presentation.view.map.viewModel.VeganMapViewModel
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


//
//import android.app.Activity
//import android.content.Context
//import android.os.Build
//import android.os.Bundle
//import android.util.DisplayMetrics
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.core.os.bundleOf
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.beginvegan.R
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.BaseFragment
//import com.example.beginvegan.databinding.FragmentVeganMapBinding
//import com.example.beginvegan.src.data.model.recipe.RecipeThree
//import com.example.beginvegan.src.data.model.restaurant.Coordinate
//import com.example.beginvegan.src.data.model.restaurant.NearRestaurant
//import com.example.beginvegan.src.data.model.restaurant.RestaurantFindInterface
//import com.example.beginvegan.src.data.model.restaurant.RestaurantFindResponse
//import com.example.beginvegan.src.data.model.restaurant.RestaurantFindService
//import com.example.beginvegan.src.ui.adapter.map.VeganMapBottomSheetRVAdapter
//import com.example.beginvegan.src.ui.view.main.MainActivity
//import com.example.beginvegan.src.ui.view.map.restaurant.RestaurantDetailFragment
//import com.example.beginvegan.util.Constants
//import com.example.beginvegan.util.Constants.RECOMMENDED_RESTAURANT
//import com.example.beginvegan.util.Constants.RESTAURANT_ID
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
//import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HALF_EXPANDED
//import net.daum.mf.map.api.MapPOIItem
//import net.daum.mf.map.api.MapPoint
//import net.daum.mf.map.api.MapView
//
///*
//* 추천 식당 클릭후 어댑터 연결 문제
//* */
//
//class VeganMapFragment : BaseFragment<FragmentVeganMapBinding>(
//    FragmentVeganMapBinding::bind,
//    R.layout.fragment_vegan_map
//), RestaurantFindInterface, MapView.POIItemEventListener {
//    private lateinit var dataList: ArrayList<NearRestaurant>
//    private lateinit var mapView: MapView
//    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
//    private lateinit var bottomSheetAdapter: VeganMapBottomSheetRVAdapter
//    private lateinit var recommendRestaurantData: NearRestaurant
//    private var recommendRestaurantTrigger = true
//    private var mContext: Context? = null
//
//    // Android Lifecycle
//    override fun onPause() {
//        super.onPause()
//        binding.mvVeganMap.removeAllViews()
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mContext = context
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // case: Recommend restaurant click
//        if (arguments != null) {
//            val d
//            ata = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                arguments?.getSerializable(RECOMMENDED_RESTAURANT, NearRestaurant::class.java)
//            } else {
//                arguments?.getSerializable(RECOMMENDED_RESTAURANT) as? NearRestaurant
//            }
//            if (data != null) {
//                recommendRestaurantData = data
//                recommendRestaurantTrigger = false
//            }
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        mContext = null
//    }
//
//    override fun init() {
//        showLoadingDialog(requireContext())
//        initializeMapView()
//        binding.veganmapBottomSheet.clBottomSheet.maxHeight = getBottomSheetDialogDefaultHeight()
//        RestaurantFindService(this).tryPostFindRestaurant(
//            Coordinate(
//                ApplicationClass.xLatitude,
//                ApplicationClass.xLongitude
//            )
//        )
//    }
//
//    // Initialize MapView & MapView Click
//    private fun initializeMapView() {
//        mapView = MapView(this@VeganMapFragment.activity)
//        binding.mvVeganMap.addView(mapView)
//        bottomSheetBehavior = BottomSheetBehavior.from(binding.veganmapBottomSheet.clBottomSheet)
//        mapView.setMapCenterPointAndZoomLevel(
//            MapPoint.mapPointWithGeoCoord(
//                ApplicationClass.xLatitude.toDouble(),
//                ApplicationClass.xLongitude.toDouble()
//            ), 4, true
//        )
//        mapView.currentLocationTrackingMode =
//            MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving
//        mapView.setOnTouchListener { _, _ ->
//            if (bottomSheetBehavior.state != STATE_COLLAPSED) {
//                bottomSheetBehavior.state = STATE_COLLAPSED
//            }
//            false
//        }
//
//    }
//
//    // BottomSheet Sizing | Height 70%
//    private fun getBottomSheetDialogDefaultHeight(): Int {
//        return getWindowHeight() * 70 / 100
//        // 위 수치는 기기 높이 대비 70%로 높이를 설정
//    }
//
//    private fun getWindowHeight(): Int {
//        val displayMetrics = DisplayMetrics()
//        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
//        return displayMetrics.heightPixels
//    }
//
//    // Restaurant's pin setting and connect MapView
//    private fun setMapViewRestaurantMarker() {
//        dataList.forEachIndexed { index, info ->
//            val marker = MapPOIItem().apply {
//                itemName = info.name
//                mapPoint = MapPoint.mapPointWithGeoCoord(
//                    info.latitude.toDouble(),
//                    info.longitude.toDouble()
//                )
//                userObject = dataList[index]
//                markerType = MapPOIItem.MarkerType.CustomImage
//                tag = index
//                customImageResourceId = R.drawable.marker_spot
//                isShowCalloutBalloonOnTouch = false
//            }
//            mapView.addPOIItem(marker)
//        }
//        mapView.setPOIItemEventListener(this)
//    }
//
//
//    private fun setBottomSheetRVAdapter() {
//        binding.veganmapBottomSheet.rvBottomSheetRestaurantList.adapter = bottomSheetAdapter
//        binding.veganmapBottomSheet.rvBottomSheetRestaurantList.layoutManager =
//            LinearLayoutManager(mContext)
//        bottomSheetAdapter.setOnItemClickListener(object :
//            VeganMapBottomSheetRVAdapter.OnItemClickListener {
//            override fun onItemClick(v: View, data: NearRestaurant, position: Int) {
//                moveRestaurantDetail(data)
//            }
//        })
//        bottomSheetBehavior.state = STATE_HALF_EXPANDED
//    }
//
//    private fun setAdapterBottomSheet() {
//        bottomSheetAdapter = VeganMapBottomSheetRVAdapter(mContext!!, dataList)
//        setBottomSheetRVAdapter()
//    }
//
//    private fun setAdapterSingleBottomSheet(data: NearRestaurant) {
//        var selectedRestaurant: ArrayList<NearRestaurant> = arrayListOf()
//        selectedRestaurant.add(data)
//        bottomSheetAdapter = VeganMapBottomSheetRVAdapter(mContext!!, selectedRestaurant)
//        mapView.setMapCenterPoint(
//            MapPoint.mapPointWithGeoCoord(
//                data.latitude.toDouble(),
//                data.longitude.toDouble()
//            ), true
//        )
//        setBottomSheetRVAdapter()
//    }
//
//    private fun moveRestaurantDetail(data: NearRestaurant) {
//        parentFragmentManager.setFragmentResult(RESTAURANT_ID, bundleOf(RESTAURANT_ID to data.id))
//        parentFragmentManager.beginTransaction().hide(this@VeganMapFragment)
//            .add(R.id.fl_main, RestaurantDetailFragment()).addToBackStack(null).commit()
//    }
//
//    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
//        setAdapterSingleBottomSheet(p1?.userObject as NearRestaurant)
//    }
//
//    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {}
//    override fun onCalloutBalloonOfPOIItemTouched(
//        p0: MapView?,
//        p1: MapPOIItem?,
//        p2: MapPOIItem.CalloutBalloonButtonType?
//    ) {
//    }
//
//    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {}
//
//
//    override fun onPostFindRestaurantSuccess(response: RestaurantFindResponse) {
//        dataList = ArrayList(response.information)
//        setMapViewRestaurantMarker()
//        if (recommendRestaurantTrigger) {
//            setAdapterBottomSheet()
//        } else {
//            setAdapterSingleBottomSheet(recommendRestaurantData)
//        }
//        dismissLoadingDialog()
//    }
//
//    override fun onPostFindRestaurantFailure(message: String) {
//        Log.d("onPostFindRestaurantFailure", message)
//    }
//
//
//}
@AndroidEntryPoint
class VeganMapFragment : BaseFragment<FragmentMainMapBinding>(R.layout.fragment_main_map) {
    private lateinit var mapView: MapView

    private val viewModel: VeganMapViewModel by viewModels()

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
                getLocation()
                startLocationUpdates()
            } else {
                logMessage("Location permission denied")
            }
        }

    override fun init() {
        initMap()

        setOnSearchBack()

        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_veganMapFragment_to_veganMapSearchFragment)
        }

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
            }
        })
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
                getLocation()
                startLocationUpdates()
            }

            else -> {
                locationPermissionLauncher.launch(permissions)
            }
        }
    }

    private fun getLocation() {
        val location: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        location?.let {
            val latitude = location.latitude
            val longitude = location.longitude
            val accuracy = location.accuracy
            val time = location.time
            logMessage("map_test, $latitude, $location, $accuracy, $time")
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

    private fun setOnSearchBack() {

    }

    companion object {
        const val SEARCH_DEFAULT = 0
        const val SEARCH_RESULT = 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        locationManager.removeUpdates(locationListener)
    }
}


//    private fun setOnSearchFocus() {
//        binding.includedSearchToolbar..onFocusChangeListener =
//            OnFocusChangeListener { v, hasFocus ->
//                if(hasFocus){
//                    binding.includedSearchToolbar.ibBack.visibility = View.VISIBLE
//                }else{
//                    binding.includedSearchToolbar.ibBack.visibility = View.GONE
//                }
//            }
//    }

//나의 식당, 나의 리뷰에서 왔을 때 처리
//    private fun checkFromMypage(){
//        val args: VeganMapFragmentArgs by navArgs()
//        Timber.d("args.fromMyRestaurant:${args.fromMyRestaurant}, args.fromMyReview:${args.fromMyReview}")
//        if(args.fromMyRestaurant){
//            //나의 식당
//            //Mypage에서 이동할때 map의 viewModel에 식당 id 넣어서 처리
//        }
//        if(args.fromMyReview){
//            //나의 리뷰
//            //Mypage에서 이동할때 map의 viewModel에 리뷰 id 넣어서 처리
//        }
//    }
