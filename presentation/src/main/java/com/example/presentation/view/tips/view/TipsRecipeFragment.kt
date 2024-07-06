package com.example.presentation.view.tips.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTipsRecipeBinding
import com.example.presentation.view.tips.adapter.TipsRecipeRvAdapter

class TipsRecipeFragment : BaseFragment<FragmentTipsRecipeBinding>(R.layout.fragment_tips_recipe) {
    private lateinit var recipeRvAdapter: TipsRecipeRvAdapter
    override fun init() {
        recipeRvAdapter = TipsRecipeRvAdapter(requireContext())
        binding.rvRecipe.adapter = recipeRvAdapter
        binding.rvRecipe.layoutManager = LinearLayoutManager(this.context)

        recipeRvAdapter.setOnItemClickListener(object : TipsRecipeRvAdapter.OnItemClickListener {
            override fun onItemClick() {
                openDialogRecipeDetail()
            }
        })

        binding.ibTooltipRecipeForMe.setOnClickListener {
            openDialogRecipeForMe()
        }
//        binding.llBtnRecipeForMe.setOnClickListener {
//            //나를 위한 레시피 필터 기능
//        }
    }

    private fun openDialogRecipeForMe(){
        TipsRecipeForMeDialog().show(childFragmentManager, "TipsRecipeForMe")
    }
    private fun openDialogRecipeDetail(){
        TipsRecipeDetailDialog().show(childFragmentManager, "TipsRecipeDetail")
    }
}