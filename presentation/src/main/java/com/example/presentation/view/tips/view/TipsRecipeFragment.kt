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
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class TipsRecipeFragment : BaseFragment<FragmentTipsRecipeBinding>(R.layout.fragment_tips_recipe) {
    @Inject
    lateinit var bookmarkController:BookmarkController
    private lateinit var recipeRvAdapter: TipsRecipeRvAdapter
    private val recipeViewModel: RecipeViewModel by activityViewModels()

    private var currentPage = 0
    private var isForMe = false
    private var recipeList = mutableListOf<TipsRecipeListItem>()
    private var totalCount = 0

    override fun init() {
        binding.lifecycleOwner = this

        reset()
        setToggleRecipeForMe()
        setListener()
        setFabButton()

        openDialogRecipeForMe()
    }
    private fun reset(){
        recipeList = mutableListOf()
        currentPage = 0
        totalCount = 0
        isForMe = false
        recipeViewModel.reSetIsContinueGetList()
        recipeViewModel.addRecipeList(recipeList)
        setAdapter(recipeList)
    }
    //api 호출
    private fun getRecipeList(page:Int){
        recipeViewModel.getRecipeList(page)
        currentPage++
    }
    private fun getRecipeForMeList(page:Int){
        recipeViewModel.getRecipeForMe(page)
        currentPage++
    }
    //RecyclerView Adapter 설정
    private fun setAdapter(list:MutableList<TipsRecipeListItem>){
        recipeRvAdapter = TipsRecipeRvAdapter(requireContext(), list)
        binding.rvRecipe.adapter = recipeRvAdapter
        binding.rvRecipe.layoutManager = LinearLayoutManager(this.context)

        recipeRvAdapter.setOnItemClickListener(object : TipsRecipeRvAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int, toggleButton: CompoundButton) {
                openDialogRecipeDetail(recipeId, toggleButton) }

            override fun changeBookmark(
                toggleButton: CompoundButton,
                isBookmarked: Boolean,
                data: TipsRecipeListItem
            ) {
                when(isBookmarked){
                    true -> {
                        lifecycleScope.launch {
                            bookmarkController.postBookmark(data.id, "RECIPE") } }
                    false -> {
                        lifecycleScope.launch {
                            bookmarkController.deleteBookmark(data.id, "RECIPE") } }
                } }
        })
    }
    private fun setListener(){
        //RecyclerView 페이징 처리
        binding.rvRecipe.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val rvPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                totalCount = recyclerView.adapter?.itemCount?.minus(1)!!
                if(rvPosition == totalCount && recipeViewModel.isContinueGetList.value!!){
                    if(isForMe){
                        getRecipeForMeList(currentPage)
                    }else{
                        getRecipeList(currentPage)
                    }
                }
            }
        })

        //api result 받으면 setRecipeList 실행
        lifecycleScope.launch {
            recipeViewModel.recipeListState.collect{state->
                when(state){
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        recipeList.addAll(state.data?.response!!)
                        recipeRvAdapter.notifyItemRangeInserted(totalCount,state.data.response.size)
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }
    }

    //나를 위한 레시피 토글 처리
    private fun setToggleRecipeForMe(){
        binding.tbRecipeForMe.setOnCheckedChangeListener { _, isChecked ->
            //유저 veganType: NONE 이면 return
            if(isChecked){
                reset()
                isForMe = true
                getRecipeForMeList(currentPage)
            }else{
                reset()
                getRecipeList(currentPage)
            }
        }
        //From VeganTest
        recipeViewModel.isFromTest.observe(this){
            if(it){
                binding.tbRecipeForMe.isChecked = true
                recipeViewModel.setIsFromTest(false)
            }else if(!binding.tbRecipeForMe.isChecked){
                getRecipeList(currentPage)
            }
        }
    }

    private fun setFabButton(){
        binding.ibFab.setOnClickListener {
            binding.rvRecipe.smoothScrollToPosition(0)
        }
    }

    //Dialog
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