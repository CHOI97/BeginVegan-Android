package com.example.presentation.view.tips.view

import android.widget.CompoundButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TipsRecipeListItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentTipsRecipeBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.tips.adapter.TipsRecipeRvAdapter
import com.example.presentation.view.tips.viewModel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class TipsRecipeFragment : BaseFragment<FragmentTipsRecipeBinding>(R.layout.fragment_tips_recipe) {
    private lateinit var recipeRvAdapter: TipsRecipeRvAdapter
    private val recipeViewModel: RecipeViewModel by activityViewModels()
    @Inject
    lateinit var bookmarkController:BookmarkController
    private var currentPage = 0
    override fun init() {
        binding.lifecycleOwner = this

        //RecyclerView List 세팅
        recipeViewModel.addRecipeList(emptyList<TipsRecipeListItem>().toMutableList())
        currentPage = 0
        getRecipeList(currentPage)

        openDialogRecipeForMe()
//        binding.llBtnRecipeForMe.setOnClickListener {
//            //나를 위한 레시피 필터 기능
//        }
    }
    private fun getRecipeList(page:Int){
        //api 호출
        recipeViewModel.getRecipeList(page)

        lifecycleScope.launch {
            recipeViewModel.recipeListState.collect{state->
                when(state){
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        //api result 받으면 setRecipeList 실행
                        setRecipeList(state.data?.response!!)
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }
        currentPage++
    }
    private fun setRecipeList(list:List<TipsRecipeListItem>){
        //RecyclerView Adapter 설정
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
        //RecyclerView 페이징 처리
        binding.rvRecipe.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val rvPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val totalCount = recyclerView.adapter?.itemCount?.minus(1)
                if(rvPosition == totalCount && recipeViewModel.isContinueGetList.value!!){
                    getRecipeList(currentPage)
                }
            }
        })
    }
    private fun openDialogRecipeDetail(recipeId:Int, toggleButton: CompoundButton){
        recipeViewModel.getRecipeDetail(recipeId)
        recipeViewModel.setSelectedTb(toggleButton)
        TipsRecipeDetailDialog().show(childFragmentManager, "TipsRecipeDetail")
    }
    private fun openDialogRecipeForMe(){
        binding.ibTooltipRecipeForMe.setOnClickListener {
            TipsRecipeForMeDialog().show(childFragmentManager, "TipsRecipeForMe")
        }
    }
}