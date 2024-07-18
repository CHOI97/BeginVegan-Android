package com.example.presentation.view.home.view

import android.widget.CompoundButton
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.tips.TipsMagazineItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentHomeTipsMagazineBinding
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.home.adapter.HomeMagazineVpAdapter
import com.example.presentation.view.home.viewModel.HomeTipsViewModel
import com.example.presentation.view.tips.viewModel.MagazineViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeTipsMagazineFragment: BaseFragment<FragmentHomeTipsMagazineBinding>(R.layout.fragment_home_tips_magazine){
    private lateinit var vpAdapter:HomeMagazineVpAdapter
    @Inject
    lateinit var bookmarkController: BookmarkController
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler

    private val viewModel:HomeTipsViewModel by viewModels()
    private val magazineViewModel:MagazineViewModel by activityViewModels()

    override fun init() {
        binding.lifecycleOwner = this
        viewModel.getHomeMagazineList()
        viewModel.homeMagazineList.observe(this){
            setAdapter(it)
        }
    }

    private fun setAdapter(list:List<TipsMagazineItem>){
        vpAdapter = HomeMagazineVpAdapter(requireContext(),list)
        binding.vpTipsMagazine.adapter = vpAdapter

        binding.ciTipsTab.setViewPager(binding.vpTipsMagazine)

        vpAdapter.setOnItemClickListener(object : HomeMagazineVpAdapter.OnItemClickListener{
            override fun onItemClick(magazineId: Int) {
                magazineViewModel.getMagazineDetail(magazineId)
                mainNavigationHandler.navigateTipsToMagazineDetail()
            }

            override fun changeBookmark(
                toggleButton: CompoundButton,
                isBookmarked: Boolean,
                data: TipsMagazineItem
            ) {
                lifecycleScope.launch {
                    if(isBookmarked) bookmarkController.postBookmark(data.id, "MAGAZINE")
                    else bookmarkController.deleteBookmark(data.id, "MAGAZINE")
                }
            }

        })
    }
}