package com.example.presentation.view.home.view

import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.model.tips.TipsMagazineItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentHomeTipsMagazineBinding
import com.example.presentation.view.home.adapter.HomeMagazineVpAdapter
import com.example.presentation.view.home.viewModel.HomeTipsMagazineViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTipsMagazineFragment: BaseFragment<FragmentHomeTipsMagazineBinding>(R.layout.fragment_home_tips_magazine){
    private lateinit var vpAdapter:HomeMagazineVpAdapter
//    private var magazineList= listOf<TipsMagazineItem>()

    private val viewModel:HomeTipsMagazineViewModel by viewModels()

    override fun init() {
        binding.lifecycleOwner = this
//        showToast("HomeTipsMagazineFragment")
        viewModel.getHomeMagazineList()
        viewModel.homeMagazineList.observe(this){
            setAdapter(it)
        }
    }

    private fun setAdapter(list:List<TipsMagazineItem>){
        vpAdapter = HomeMagazineVpAdapter(requireContext(),list)
        binding.vpTipsMagazine.adapter = vpAdapter

        binding.ciTipsTab.setViewPager(binding.vpTipsMagazine)
    }
}