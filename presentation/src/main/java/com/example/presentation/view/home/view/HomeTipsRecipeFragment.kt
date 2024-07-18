package com.example.presentation.view.home.view

import android.widget.CompoundButton
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.TipsRecipeListItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.FragmentHomeTipsRecipeBinding
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.home.adapter.HomeRecipeVpAdapter
import com.example.presentation.view.home.viewModel.HomeTipsViewModel
import com.example.presentation.view.tips.view.TipsRecipeDetailDialog
import com.example.presentation.view.tips.viewModel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeTipsRecipeFragment: BaseFragment<FragmentHomeTipsRecipeBinding>(R.layout.fragment_home_tips_recipe){
    private lateinit var vpAdapter: HomeRecipeVpAdapter
    @Inject
    lateinit var bookmarkController: BookmarkController
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler

    private val viewModel: HomeTipsViewModel by viewModels()
    private val recipeViewModel: RecipeViewModel by activityViewModels()

    override fun init() {
        binding.lifecycleOwner = this
        viewModel.getHomeRecipeList()
        viewModel.homeRecipeList.observe(this){
            setAdapter(it)
        }
    }

    private fun setAdapter(list:List<TipsRecipeListItem>){
        vpAdapter = HomeRecipeVpAdapter(requireContext(),list)
        binding.vpTipsRecipe.adapter = vpAdapter

        binding.ciTipsRecipe.setViewPager(binding.vpTipsRecipe)

        vpAdapter.setOnItemClickListener(object : HomeRecipeVpAdapter.OnItemClickListener{
            override fun onItemClick(recipeId: Int,toggleButton: CompoundButton) {
                openDialogRecipeDetail(recipeId, toggleButton)
            }

            override fun changeBookmark(
                toggleButton: CompoundButton,
                isBookmarked: Boolean,
                data: TipsRecipeListItem
            ) {
                lifecycleScope.launch {
                    if(isBookmarked) bookmarkController.postBookmark(data.id, "RECIPE")
                    else bookmarkController.deleteBookmark(data.id, "RECIPE")
                }
            }

        })
    }

    //Dialog
    private fun openDialogRecipeDetail(recipeId:Int, toggleButton: CompoundButton){
        recipeViewModel.getRecipeDetail(recipeId)
        recipeViewModel.setSelectedTb(toggleButton)
        TipsRecipeDetailDialog().show(childFragmentManager, "MyRecipeDetail")
    }
}