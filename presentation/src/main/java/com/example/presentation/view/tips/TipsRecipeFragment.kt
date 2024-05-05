package com.example.presentation.view.tips

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTipsRecipeBinding
import com.example.presentation.view.mypage.MypageDeleteAccountDialog

class TipsRecipeFragment : BaseFragment<FragmentTipsRecipeBinding>(R.layout.fragment_tips_recipe) {
    private lateinit var recipeRvAdapter: TipsRecipeRvAdapter
    override fun init() {
        recipeRvAdapter = TipsRecipeRvAdapter(requireContext())
        binding.rvRecipe.adapter = recipeRvAdapter
        binding.rvRecipe.layoutManager = LinearLayoutManager(this.context)

        recipeRvAdapter.setOnItemClickListener(object :TipsRecipeRvAdapter.OnItemClickListener{
            override fun onItemClick() {

            }
        })

        binding.ibTooltipRecipeForMe.setOnClickListener {
            openDialogRecipeForMe()
        }
    }

    private fun openDialogRecipeForMe(){
        TipsRecipeForMeDialog().show(childFragmentManager, "TipsRecipeForMe")
    }
}