package com.example.presentation.view.mypage.view

import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
import com.example.presentation.view.main.MainFragment
import com.example.presentation.view.mypage.adapter.MyMagazineRvAdapter
import com.example.presentation.view.mypage.viewModel.MyScrapViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MypageMyMagazineFragment : BaseFragment<FragmentMypageMyMagazineBinding>(R.layout.fragment_mypage_my_magazine) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    @Inject
    lateinit var bookmarkController: BookmarkController

    private val myScrapViewModel: MyScrapViewModel by activityViewModels()
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
        myScrapViewModel.reSetIsContinueGetList()
        myScrapViewModel.setMyMagazineList(myMagazineList)
    }
    private fun setRvAdapter(){
        myMagazineRvAdapter = MyMagazineRvAdapter(requireContext(), myMagazineList)
        binding.rvMyMagazine.adapter = myMagazineRvAdapter
        binding.rvMyMagazine.layoutManager = LinearLayoutManager(this.context)

        myMagazineRvAdapter.setOnItemClickListener(object :MyMagazineRvAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                //Magazine Detail로 이동
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
                        Timber.d("viewModel collect list:${state.data?.response!!}")
                        myMagazineList.addAll(state.data?.response!!)
                        myMagazineRvAdapter.notifyItemRangeInserted(totalCount,state.data.response.size)
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
            //클릭시 상단으로 이동
        }
    }
    private fun setEmptyState(){
        binding.llEmptyArea.isVisible = true
        binding.btnMoveToMagazine.setOnClickListener {
            //Recipe로 이동
        }
    }
}