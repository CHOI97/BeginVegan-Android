package com.example.presentation.view.mypage.view

import android.widget.CompoundButton
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.mypage.MypageMyRecipeItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentMypageMyRecipeBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.mypage.adapter.MyRecipeRvAdapter
import com.example.presentation.view.mypage.viewModel.MyRecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MypageMyRecipeFragment : BaseFragment<FragmentMypageMyRecipeBinding>(R.layout.fragment_mypage_my_recipe) {
    @Inject
    lateinit var mainNavigationHandler:MainNavigationHandler
    @Inject
    lateinit var bookmarkController: BookmarkController
    private val myRecipeViewModel:MyRecipeViewModel by activityViewModels()

    private lateinit var myRecipeRvAdapter:MyRecipeRvAdapter
    private var myRecipeList = mutableListOf<MypageMyRecipeItem>()
    private var currentPage = 0
    private var totalCount = 0
    override fun init() {
        binding.lifecycleOwner = this
        setBackUp()
        setFabButton()

        reset()
        setRvAdapter()
        setEmptyState()
    }
    private fun reset(){
        myRecipeList = mutableListOf()
        myRecipeViewModel.setMyRecipeList(myRecipeList)
        currentPage = 0
        totalCount = 0
    }
    private fun setRvAdapter(){
        myRecipeRvAdapter = MyRecipeRvAdapter(requireContext(), myRecipeList)
        binding.rvMyRecipe.adapter = myRecipeRvAdapter
        binding.rvMyRecipe.layoutManager = LinearLayoutManager(this.context)

        myRecipeRvAdapter.setOnItemClickListener(object : MyRecipeRvAdapter.OnItemClickListener{
            override fun onItemClick(id: Int, toggleButton: CompoundButton) {
                //Magazine Detail로 이동
            }

            override fun setToggleButton(isChecked: Boolean, recipeId: Int) {
                lifecycleScope.launch {
                    if(isChecked) bookmarkController.postBookmark(recipeId, "RECIPE")
                    else bookmarkController.deleteBookmark(recipeId, "RECIPE")
                }
            }
        })

        setListener()
        myRecipeViewModel.setMyRecipeList(myRecipeList)
        getMyRecipeList()
    }
    private fun getMyRecipeList(){
        myRecipeViewModel.getMyRecipe(currentPage)
        currentPage++
    }
    private fun setListener(){
        //page
        binding.rvMyRecipe.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val rvPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                totalCount = recyclerView.adapter?.itemCount?.minus(1)!!
                if(rvPosition == totalCount && myRecipeViewModel.isContinueGetList.value!!){
                    getMyRecipeList()
                }
            }
        })

        lifecycleScope.launch {
            myRecipeViewModel.myRecipesState.collect{state->
                when(state){
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        myRecipeList.addAll(state.data?.response!!)
                        myRecipeRvAdapter.notifyItemRangeInserted(totalCount,state.data.response.size)
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }
    }

    private fun setBackUp(){
        binding.includedToolbar.ibBackUp.setOnClickListener {
            mainNavigationHandler.popBackStack()
        }
    }
    private fun setFabButton(){
        binding.ibFab.setOnClickListener {
            binding.rvMyRecipe.smoothScrollToPosition(0)
        }
    }
    private fun setEmptyState(){
        myRecipeViewModel.isRecipeEmpty.observe(this){
            binding.llEmptyArea.isVisible = it
            binding.btnMoveToRecipe.setOnClickListener {
                //Recipe로 이동
            }
        }
    }
}