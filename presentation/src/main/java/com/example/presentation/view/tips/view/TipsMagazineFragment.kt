package com.example.presentation.view.tips.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTipsMagazineBinding
import com.example.presentation.view.tips.adapter.TipsMagazineRvAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TipsMagazineFragment : BaseFragment<FragmentTipsMagazineBinding>(R.layout.fragment_tips_magazine) {
    private lateinit var magazineRvAdapter: TipsMagazineRvAdapter
    override fun init() {
        magazineRvAdapter = TipsMagazineRvAdapter(requireContext())
        binding.rvMagazine.adapter = magazineRvAdapter
        binding.rvMagazine.layoutManager = LinearLayoutManager(this.context)

        magazineRvAdapter.setOnItemClickListener(object :
            TipsMagazineRvAdapter.OnItemClickListener {
            override fun onItemClick() {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fcw_home, TipsMagazineDetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
        })
    }
}