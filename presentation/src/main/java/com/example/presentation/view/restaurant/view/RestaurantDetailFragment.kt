package com.example.presentation.view.restaurant.view

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.data.model.map.RestaurantType
import com.example.domain.model.map.RestaurantDetail
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageMyRestaurantBinding
import com.example.presentation.databinding.FragmentRestaurantDetailBinding
import com.example.presentation.view.restaurant.viewModel.RestaurantViewModel
import kotlinx.coroutines.launch

class RestaurantDetailFragment :
    BaseFragment<FragmentRestaurantDetailBinding>(R.layout.fragment_restaurant_detail) {
    private val args: RestaurantDetailFragmentArgs by navArgs()

    private val viewModel: RestaurantViewModel by viewModels()
    override fun init() {
        args?.let {
            viewModel.getRestaurantDetail(it.restaurantId, it.latitude, it.longitude)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.restaurantDetail.collect {
                logMessage("viewLifecycleOwner collect restaurantDetail $it")
                initRestaurantDetail(it)
            }
        }
    }

    private fun initRestaurantDetail(data: RestaurantDetail) {
        binding.tvTitleRestaurantName.text = data.name
        Glide.with(requireContext()).load(args.imgUrl).into(binding.ivRestaurantImg)
        binding.tvRestaurantName.text = data.name
        binding.tvRestaurantType.text = RestaurantType.getKoreanNameFromEng(data.restaurantType)
        binding.tvRestaurantAddress.text = "${data.address.province} ${data.address.city} ${data.address.roadName} ${data.address.detailAddress}"
        binding.tvRestaurantScore.text = if (data.rate == null) {
            "0.0"
        } else {
            String.format("%.1f", data.rate)
        }
        binding.tvRestaurantReviewCount.text = data.reviewCount.toString()
    }

    private fun setPageNavigationButton(){
        binding.btnNavFind.setOnClickListener {
            showToast("준비중: 길찾기")
        }
        binding.btnNavCall.setOnClickListener {
            showToast("준비중: 전화")
        }
        binding.btnNavLike.setOnClickListener {
            showToast("준비중: 저장")
        }
        binding.btnNavEdit.setOnClickListener {
            showToast("준비중: 수정")
        }
    }
}