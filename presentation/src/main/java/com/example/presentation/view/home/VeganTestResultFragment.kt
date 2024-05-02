package com.example.presentation.view.home

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentVeganTestResultBinding

class VeganTestResultFragment : BaseFragment<FragmentVeganTestResultBinding>(R.layout.fragment_vegan_test_result) {
    override fun init() {
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
        //레시피로 이동
    }
}