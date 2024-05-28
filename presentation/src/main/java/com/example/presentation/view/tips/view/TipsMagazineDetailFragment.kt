package com.example.presentation.view.tips.view

import androidx.fragment.app.activityViewModels
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentTipsMagazineDetailBinding
import com.example.presentation.view.tips.viewModel.MagazineViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TipsMagazineDetailFragment : BaseFragment<FragmentTipsMagazineDetailBinding>(R.layout.fragment_tips_magazine_detail) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler

    private val magazineViewModel:MagazineViewModel by activityViewModels()

    override fun init() {
        binding.lifecycleOwner = this
        binding.tvMagazineTitle.text = magazineViewModel.selectedMagazineId.value.toString()
        goBackUp()
    }

    private fun goBackUp(){
        binding.includedToolbar.ibBackUp.setOnClickListener {
            mainNavigationHandler.popBackStack()
        }
    }
}