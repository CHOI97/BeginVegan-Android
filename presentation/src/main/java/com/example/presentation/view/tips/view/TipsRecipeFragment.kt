package com.example.presentation.view.tips.view

import android.graphics.Typeface
import android.widget.CompoundButton
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.tips.CheckChange
import com.example.domain.model.tips.RecipeDetailPosition
import com.example.domain.model.tips.TipsRecipeListItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.FragmentTipsRecipeBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.tips.adapter.TipsRecipeRvAdapter
import com.example.presentation.view.tips.viewModel.RecipeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class TipsRecipeFragment : BaseFragment<FragmentTipsRecipeBinding>(R.layout.fragment_tips_recipe) {
    @Inject
    lateinit var bookmarkController:BookmarkController
    @Inject
    lateinit var mainNavigationHandler:MainNavigationHandler
    private lateinit var recipeRvAdapter: TipsRecipeRvAdapter
    private val recipeViewModel: RecipeViewModel by activityViewModels()

    private var currentPage = 0
    private var isForMe = false
//    private var recipeList = mutableListOf<TipsRecipeListItem>()
    private var totalCount = 0

    private var typeface:Typeface? = null
    private var job: Job? = null

    override fun init() {
        binding.lifecycleOwner = this
        typeface = ResourcesCompat.getFont(requireContext(), R.font.pretendard_regular)
        job?.cancel()

        reset()
        setToggleRecipeForMe()
        setListener()
        setFabButton()

        openDialogRecipeForMe()
    }
    private fun reset(){
//        recipeList = mutableListOf()
        currentPage = 0
        totalCount = 0
        isForMe = false
        recipeViewModel.reSetIsContinueGetList()
        recipeViewModel.resetRecipeList()
        setAdapter()
//        setAdapter(recipeList)
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
    private fun setAdapter(){
        recipeRvAdapter = TipsRecipeRvAdapter(requireContext(), mutableListOf())
        binding.rvRecipe.adapter = recipeRvAdapter
        binding.rvRecipe.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        recipeRvAdapter.setOnItemClickListener(object : TipsRecipeRvAdapter.OnItemClickListener {
            override fun onItemClick(item: TipsRecipeListItem, position: Int) {
                openDialogRecipeDetail(item, position)
            }

            override fun changeBookmark(
                toggleButton: CompoundButton,
                isBookmarked: Boolean,
                data: TipsRecipeListItem
            ) {
                lifecycleScope.launch {
                    if(isBookmarked){
                        if(bookmarkController.postBookmark(data.id, "RECIPE")){
                            setSnackBar(getString(R.string.toast_scrap_done))
                        }
                    }else{
                        if(bookmarkController.deleteBookmark(data.id, "RECIPE")){
                            setSnackBar(getString(R.string.toast_scrap_undo))
                        }
                    }
                }
             }
        })
    }
    private fun setListener(){
        //RecyclerView 페이징 처리
        binding.rvRecipe.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val rvPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                totalCount = recyclerView.adapter?.itemCount?.minus(1)!!
//                Timber.d("currentPAge: $currentPage, recipeViewModel.isContinueGetList.value!!: ${recipeViewModel.isContinueGetList.value!!}")
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
                        if(state.data?.response?.size != 0){
                            if(state.data?.response!!.isNotEmpty()){
                                Timber.d("state.data?.response!!: ${state.data.response.size}")
                                val newList = mutableListOf<TipsRecipeListItem>()
                                newList.addAll(state.data.response)
                                recipeRvAdapter.submitList(newList)
                            }
                        }
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }

//        job = lifecycleScope.launch {
//            recipeViewModel.checkChange.collect{
//                Timber.d("recipeViewModel.checkChange.collect: ${it.check}")
//                if(it.check) {
//                    Timber.d("recipeViewModel.checkChange.collect: ${it.position}")
////                    recipeRvAdapter.notifyItemChanged(it.position)
////                    recipeRvAdapter.notifyChange(it.position)
////                    recipeRvAdapter.updateRecipeList(recipeViewModel.newRecipeList.value!!)
////                    recipeRvAdapter.submitList(recipeViewModel.newRecipeList.value!!)
//                    recipeViewModel.setCheckChange(CheckChange(false, 0))
//                }
//            }
//        }
//        recipeViewModel.recipeListState.value.data.response.ob
//        lifecycleScope.launch {
//            recipeViewModel.recipeList.collect{
//                Timber.d("recipeRvAdapter.differ.submitList(it) 실행")
////                recipeRvAdapter.differ.submitList(it)
//            }
//        }
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
    private fun openDialogRecipeDetail(item: TipsRecipeListItem, position: Int){
        recipeViewModel.getRecipeDetail(item.id)
        recipeViewModel.setNowFragment("RECIPE")
        recipeViewModel.setRecipeDetailPosition(RecipeDetailPosition(position, item))
        TipsRecipeDetailDialog().show(childFragmentManager, "TipsRecipeDetail")
    }
    private fun openDialogRecipeForMe(){
        binding.ibTooltipRecipeForMe.setOnClickListener {
            TipsRecipeForMeDialog().show(childFragmentManager, "TipsRecipeForMe")
        }
    }

    //SnackBar
    private fun setSnackBar(message:String){
        val snackbar = Snackbar.make(binding.clLayout, message, Snackbar.LENGTH_SHORT)
            .setAction(getString(R.string.toast_scrap_action)){
                mainNavigationHandler.navigateTipsRecipeToMyRecipe()
            }
            .setActionTextColor(resources.getColor(R.color.color_primary_variant_02))
        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTypeface(typeface)
        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action).setTypeface(typeface)
        snackbar.show()
    }

}