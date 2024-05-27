package com.example.presentation.view.tips.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentTipsMagazineBinding
import com.example.presentation.view.tips.adapter.TipsMagazineRvAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TipsMagazineFragment : BaseFragment<FragmentTipsMagazineBinding>(R.layout.fragment_tips_magazine) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler

    private lateinit var magazineRvAdapter: TipsMagazineRvAdapter

    override fun init() {
        magazineRvAdapter = TipsMagazineRvAdapter(requireContext())
        binding.rvMagazine.adapter = magazineRvAdapter
        binding.rvMagazine.layoutManager = LinearLayoutManager(this.context)

        magazineRvAdapter.setOnItemClickListener(object :
            TipsMagazineRvAdapter.OnItemClickListener {
            override fun onItemClick() {
                mainNavigationHandler.navigateToTipsMagazineDetail()
            }
        })
    }
}