package com.example.presentation.view.home

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.home.HomeNavigationHandler
import com.example.presentation.config.navigation.home.HomeNavigationImpl
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentVeganTestResultBinding
import com.example.presentation.util.VeganTypes
import com.example.presentation.view.home.viewModel.VeganTestViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VeganTestResultFragment : BaseFragment<FragmentVeganTestResultBinding>(R.layout.fragment_vegan_test_result) {

    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    private val viewModel:VeganTestViewModel by activityViewModels()

    override fun init() {
        viewModel.userVeganType.observe(this, Observer {
            val veganType = viewModel.userVeganType.value!!
            binding.tvResultVeganType.text = VeganTypes.valueOf(veganType).veganType
        })

        binding.includedToolbar.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

//        binding.tvDescription.text = 유저네임
//        binding.tvResultVeganType.text = 비건타입
//        binding.tvVeganTypeDescription.text = 비건타입 설명
//        binding.tvResultExplanation.text = 비건 타입 상세 설명
//        binding.tvGoRecommendRecipe.text = 유저 네임
//        binding.includedIllusVeganLevel. //일러스트

        binding.tvBtnGoRecommendRecipe.setOnClickListener {
            goRecommendRecipe()
        }
    }

    private fun goRecommendRecipe(){
        mainNavigationHandler.navigateToMainHome()
    }
}