package com.example.presentation.view.tips.view

import android.widget.CompoundButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.TipsRecipeListItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTipsRecipeBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.tips.adapter.TipsRecipeRvAdapter
import com.example.presentation.view.tips.viewModel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TipsRecipeFragment : BaseFragment<FragmentTipsRecipeBinding>(R.layout.fragment_tips_recipe) {
    private lateinit var recipeRvAdapter: TipsRecipeRvAdapter
    private val recipeViewModel: RecipeViewModel by activityViewModels()
    @Inject
    lateinit var bookmarkController:BookmarkController
    override fun init() {
        binding.lifecycleOwner = this

        getRecipeList()

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

    private fun getRecipeList(){
        recipeViewModel.getRecipeList()
        lifecycleScope.launch {
            recipeViewModel.recipeList.collect{state->
                when(state){
                    is NetworkResult.Loading -> {

                    }
                    is NetworkResult.Success -> {
                        setRecipeList(state.data?.response!!)
                    }
                    is NetworkResult.Error -> {

                    }
                }
            }
        }

    }
    private fun setRecipeList(list:List<TipsRecipeListItem>){
        recipeRvAdapter = TipsRecipeRvAdapter(requireContext(), list)
        binding.rvRecipe.adapter = recipeRvAdapter
        binding.rvRecipe.layoutManager = LinearLayoutManager(this.context)

        recipeRvAdapter.setOnItemClickListener(object : TipsRecipeRvAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int, toggleButton: CompoundButton) {
                openDialogRecipeDetail(recipeId, toggleButton)
            }

            override fun changeBookmark(
                toggleButton: CompoundButton,
                isBookmarked: Boolean,
                data: TipsRecipeListItem
            ) {
                when(isBookmarked){
                    true -> {
                        lifecycleScope.launch {
                            bookmarkController.postBookmark(data.id, "RECIPE")
                        }
                    }
                    false -> {
                        lifecycleScope.launch {
                            bookmarkController.deleteBookmark(data.id, "RECIPE")
                        }
                    }
                }
            }
        })
    }
    private fun openDialogRecipeDetail(recipeId:Int, toggleButton: CompoundButton){
        recipeViewModel.getRecipeDetail(recipeId)
        recipeViewModel.setSelectedTb(toggleButton)
        TipsRecipeDetailDialog().show(childFragmentManager, "TipsRecipeDetail")
    }
}