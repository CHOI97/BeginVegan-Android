package com.example.presentation.view.tips.view

import android.view.View
import android.widget.CompoundButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.tips.MagazineResponse
import com.example.domain.model.TipsRecipeListItem
import com.example.domain.model.tips.TipsMagazineItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentTipsMagazineBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.tips.adapter.TipsMagazineRvAdapter
import com.example.presentation.view.tips.viewModel.MagazineViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class TipsMagazineFragment : BaseFragment<FragmentTipsMagazineBinding>(R.layout.fragment_tips_magazine) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    @Inject
    lateinit var bookmarkController: BookmarkController
    private val magazineViewModel : MagazineViewModel by activityViewModels()
    private lateinit var magazineRvAdapter: TipsMagazineRvAdapter
    private var collectJob: Job? = null

    private var recipeList = mutableListOf<TipsMagazineItem>()
    private var currentPage = 0
    private var totalCount = 0

    override fun init() {
        binding.lifecycleOwner = this
        reset()
        setRvAdapter()
        setListener()
        setTabBtn()
    }
    private fun reset(){
        collectJob?.cancel()
        recipeList = mutableListOf()
        magazineViewModel.addMagazineList(recipeList)
        magazineViewModel.reSetIsContinueGetList()
        currentPage = 0
        totalCount = 0
    }

    private fun setRvAdapter(){
        magazineRvAdapter = TipsMagazineRvAdapter(requireContext(), recipeList)
        binding.rvMagazine.adapter = magazineRvAdapter
        binding.rvMagazine.layoutManager = LinearLayoutManager(this.context)

        magazineRvAdapter.setOnItemClickListener(object :
            TipsMagazineRvAdapter.OnItemClickListener {
            override fun onItemClick(magazineId:Int) {
                mainNavigationHandler.navigateToTipsMagazineDetail()
                magazineViewModel.setSelectedMagazineId(magazineId) }

            override fun changeBookmark(
                toggleButton: CompoundButton,
                isBookmarked: Boolean,
                data: TipsMagazineItem
            ) { when(isBookmarked){
                true -> {
                    lifecycleScope.launch {
                        bookmarkController.postBookmark(data.id, "MAGAZINE") } }
                false -> {
                    lifecycleScope.launch {
                        bookmarkController.deleteBookmark(data.id, "MAGAZINE") } }
            } }
        })

        getMagazineList(currentPage)
    }
    private fun getMagazineList(page:Int){
        magazineViewModel.getMagazineList(page)
        currentPage++
    }
    private fun setListener(){
        //RecyclerView 페이징 처리
        binding.rvMagazine.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val rvPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                totalCount = recyclerView.adapter?.itemCount?.minus(1)!!
                if(rvPosition == totalCount && magazineViewModel.isContinueGetList.value!!){
                    getMagazineList(currentPage)
                }
            }
        })

        //api result 받으면 setRecipeList 실행
        collectJob = lifecycleScope.launch {
            magazineViewModel.magazineListState.collect{state->
                when(state){
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        recipeList.addAll(state.data?.response!!)
                        magazineRvAdapter.notifyItemRangeInserted(totalCount,state.data.response.size)
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }
    }

    private fun setTabBtn(){
        binding.ibFab.setOnClickListener {
            binding.rvMagazine.smoothScrollToPosition(0)
        }
    }
}