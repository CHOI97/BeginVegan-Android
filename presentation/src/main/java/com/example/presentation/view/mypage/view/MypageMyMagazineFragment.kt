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
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MypageMyMagazineFragment : BaseFragment<FragmentMypageMyMagazineBinding>(R.layout.fragment_mypage_my_magazine) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    @Inject
    lateinit var bookmarkController: BookmarkController
    private val magazineViewModel:MagazineViewModel by activityViewModels()

    private val myScrapViewModel: MyMagazineViewModel by activityViewModels()
    private lateinit var myMagazineRvAdapter: MyMagazineRvAdapter
    private val myMagazineList = mutableListOf<MypageMyMagazineItem>()
    private var currentPage = 0
    private var totalCount = 0
    override fun init() {
        binding.lifecycleOwner = this
        setBackUp()
        setFabButton()

        reset()
        setRvAdapter()
        setListener()
    }
    private fun reset(){
        myScrapViewModel.reSetVieModel()
//        myScrapViewModel.setMyMagazineList(myMagazineList)
    }
    private fun setRvAdapter(){
        myMagazineRvAdapter = MyMagazineRvAdapter(requireContext(), myMagazineList)
        binding.rvMyMagazine.adapter = myMagazineRvAdapter
        binding.rvMyMagazine.layoutManager = LinearLayoutManager(this.context)

        myMagazineRvAdapter.setOnItemClickListener(object :MyMagazineRvAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                //Magazine Detail로 이동
                mainNavigationHandler.navigationMyMagazineToMagazineDetail()
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

        getMyMagazineList()
    }
    private fun getMyMagazineList(){
        myScrapViewModel.getMyMagazine(currentPage)
        currentPage++
    }
    private fun setListener(){
        //page
        binding.rvMyMagazine.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val rvPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                totalCount = recyclerView.adapter?.itemCount?.minus(1)!!
                if(rvPosition == totalCount && myScrapViewModel.isContinueGetList.value!!){
                    getMyMagazineList()
                }
            }
        })

        lifecycleScope.launch {
            myScrapViewModel.myMagazineState.collect{state->
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

        myScrapViewModel.isMagazineEmpty.observe(this){
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
            //Recipe로 이동
        }
    }
}