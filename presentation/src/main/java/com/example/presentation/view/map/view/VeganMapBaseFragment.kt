package com.example.presentation.view.map.view

import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainMapBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.search.SearchView
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class VeganMapBaseFragment : BaseFragment<FragmentMainMapBinding>(R.layout.fragment_main_map) {
    private lateinit var mapView: MapView
    override fun init() {
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

        //나의 식당, 나의 리뷰에서 왔을 때 처리

    }

    //    private fun setSearchQuery() {
//        binding.svSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // 검색 버튼
//
//                binding.tbSearchView
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // 글자변경
//                return true
//            }
//
//        })
//    }
//
//    private fun showCollapased() {
//        binding.tbSearchView.collapseActionView()
//    }

    private fun checkFromMypage() {
        val args: VeganMapBaseFragmentArgs by navArgs()
        Timber.d("args.fromMyRestaurant:${args.fromMyRestaurant}, args.fromMyReview:${args.fromMyReview}")
        if (args.fromMyRestaurant) {
            //나의 식당
            //Mypage에서 이동할때 map의 viewModel에 식당 id 넣어서 처리
        }
        if (args.fromMyReview) {
            //나의 리뷰
            //Mypage에서 이동할때 map의 viewModel에 리뷰 id 넣어서 처리
        }
    }

}