package com.example.presentation.view.mypage.view

import android.widget.CompoundButton
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.mypage.MypageMyRecipeItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.FragmentMypageMyRecipeBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.main.MainViewModel
import com.example.presentation.view.mypage.adapter.MyRecipeRvAdapter
import com.example.presentation.view.mypage.viewModel.MyRecipeViewModel
import com.example.presentation.view.tips.view.TipsRecipeDetailDialog
import com.example.presentation.view.tips.viewModel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MypageMyRecipeFragment : BaseFragment<FragmentMypageMyRecipeBinding>(R.layout.fragment_mypage_my_recipe) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    @Inject
    lateinit var bookmarkController: BookmarkController
    private val myRecipeViewModel:MyRecipeViewModel by viewModels()
    private val recipeViewModel:RecipeViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by navGraphViewModels(R.id.nav_main_graph)

    private lateinit var myRecipeRvAdapter:MyRecipeRvAdapter
    private var myRecipeList = mutableListOf<MypageMyRecipeItem>()
    private var currentPage = 0
    private var totalCount = 0
    private var collectJob: Job? = null

    override fun init() {
        binding.lifecycleOwner = this
        setBackUp()
        setFabButton()

        reset()
        setRvAdapter()
    }
    private fun reset(){
        collectJob?.cancel()
        myRecipeList = mutableListOf()
        myRecipeViewModel.resetViewModel()
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
                openDialogRecipeDetail(id, toggleButton)
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

        collectJob = lifecycleScope.launch {
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

        myRecipeViewModel.isRecipeEmpty.observe(this){
            setEmptyState(it)
        }
    }

    private fun setBackUp(){
        binding.includedToolbar.ibBackUp.setOnClickListener {
//            mainNavigationHandler.popBackStack()
            findNavController().popBackStack()
        }
    }
    private fun setFabButton(){
        binding.ibFab.setOnClickListener {
            binding.rvMyRecipe.smoothScrollToPosition(0)
        }
    }
    private fun setEmptyState(emptyState:Boolean){
        binding.llEmptyArea.isVisible = emptyState
        binding.btnMoveToRecipe.setOnClickListener {
            //Recipe로 이동
            mainViewModel.setTipsMoveToRecipe(true)
            mainNavigationHandler.navigateMyRecipeToTips()
        }
    }

    //Dialog
    private fun openDialogRecipeDetail(recipeId:Int, toggleButton: CompoundButton){
        recipeViewModel.getRecipeDetail(recipeId)
        recipeViewModel.setSelectedTb(toggleButton)
        TipsRecipeDetailDialog().show(childFragmentManager, "MyRecipeDetail")
    }
}