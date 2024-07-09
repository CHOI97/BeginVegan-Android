package com.example.presentation.view.tips.view

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.TipsRecipeListItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTipsRecipeBinding
import com.example.presentation.view.tips.adapter.TipsRecipeRvAdapter
import com.example.presentation.view.tips.viewModel.RecipeViewModel

class TipsRecipeFragment : BaseFragment<FragmentTipsRecipeBinding>(R.layout.fragment_tips_recipe) {
    private lateinit var recipeRvAdapter: TipsRecipeRvAdapter
    private val recipeViewModel: RecipeViewModel by activityViewModels()
    override fun init() {
        binding.lifecycleOwner = this

        recipeViewModel.recipeList.observe(this){
            setRecipeList(it)
        }

        openDialogRecipeForMe()
        binding.llBtnRecipeForMe.setOnClickListener {
            //나를 위한 레시피 필터 기능
        }
    }

    private fun openDialogRecipeForMe(){
        binding.ibTooltipRecipeForMe.setOnClickListener {
            TipsRecipeForMeDialog().show(childFragmentManager, "TipsRecipeForMe")
        }
    }


    private fun setRecipeList(list:List<TipsRecipeListItem>){
        recipeRvAdapter = TipsRecipeRvAdapter(requireContext(), list)
        binding.rvRecipe.adapter = recipeRvAdapter
        binding.rvRecipe.layoutManager = LinearLayoutManager(this.context)

        recipeRvAdapter.setOnItemClickListener(object : TipsRecipeRvAdapter.OnItemClickListener {
            override fun onItemClick() {
                openDialogRecipeDetail()
            }
        })
    }
    private fun openDialogRecipeDetail(){
        TipsRecipeDetailDialog().show(childFragmentManager, "TipsRecipeDetail")
    }
}