package com.example.presentation.view.mypage.view

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.mypage.MypageMyMagazineItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentMypageMyMagazineBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.mypage.adapter.MyMagazineRvAdapter
import com.example.presentation.view.mypage.viewModel.MyMagazineViewModel
import com.example.presentation.view.tips.viewModel.MagazineViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MypageMyMagazineFragment : BaseFragment<FragmentMypageMyMagazineBinding>(R.layout.fragment_mypage_my_magazine) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    @Inject
    lateinit var bookmarkController: BookmarkController
    private val magazineViewModel:MagazineViewModel by activityViewModels()

    private val myMagazineViewModel: MyMagazineViewModel by activityViewModels()
    private lateinit var myMagazineRvAdapter: MyMagazineRvAdapter
    private var myMagazineList = mutableListOf<MypageMyMagazineItem>()
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
        myMagazineList = mutableListOf()
        currentPage = 0
        totalCount = 0
        myMagazineViewModel.reSetVieModel()
    }
    private fun setRvAdapter(){
        myMagazineRvAdapter = MyMagazineRvAdapter(requireContext(), myMagazineList)
        binding.rvMyMagazine.adapter = myMagazineRvAdapter
        binding.rvMyMagazine.layoutManager = LinearLayoutManager(this.context)

        myMagazineRvAdapter.setOnItemClickListener(object :MyMagazineRvAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                //Magazine Detail로 이동
                mainNavigationHandler.navigateMyMagazineToMagazineDetail()
                magazineViewModel.resetMagazineDetail()
                magazineViewModel.getMagazineDetail(id)
            }

            override fun setToggleButton(isChecked: Boolean, magazineId: Int) {
                lifecycleScope.launch {
                    if(isChecked) bookmarkController.postBookmark(magazineId, "MAGAZINE")
                    else bookmarkController.deleteBookmark(magazineId, "MAGAZINE")
                }
            }
        })

        setListener()
        myMagazineViewModel.setMyMagazineList(myMagazineList)
        getMyMagazineList()
    }
    private fun getMyMagazineList(){
        myMagazineViewModel.getMyMagazine(currentPage)
        currentPage++
    }
    private fun setListener(){
        //page
        binding.rvMyMagazine.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val rvPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                totalCount = recyclerView.adapter?.itemCount?.minus(1)!!
                if(rvPosition == totalCount && myMagazineViewModel.isContinueGetList.value!!){
                    getMyMagazineList()
                }
            }
        })

        collectJob = lifecycleScope.launch {
            myMagazineViewModel.myMagazineState.collect{state->
                when(state){
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        myMagazineList.addAll(state.data?.response!!)
                        myMagazineRvAdapter.notifyItemRangeInserted(totalCount,state.data.response.size)
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }

        myMagazineViewModel.isMagazineEmpty.observe(this){
            setEmptyState(it)
        }

    }
    private fun setBackUp(){
        binding.includedToolbar.ibBackUp.setOnClickListener {
            mainNavigationHandler.popBackStack()
        }
    }
    private fun setFabButton(){
        binding.ibFab.setOnClickListener {
            binding.rvMyMagazine.smoothScrollToPosition(0)
        }
    }
    private fun setEmptyState(emptyState:Boolean){
        binding.llEmptyArea.isVisible = emptyState
        binding.btnMoveToMagazine.setOnClickListener {
            mainNavigationHandler.navigateMyMagazineToMainHome(true)
        }
    }
}