package com.example.presentation.view.mypage.view

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.mypage.MypageMyRestaurantItem
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.FragmentMypageMyRestaurantBinding
import com.example.presentation.network.NetworkResult
import com.example.presentation.view.mypage.adapter.MyRestaurantRvAdapter
import com.example.presentation.view.mypage.viewModel.MyRestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MypageMyRestaurantFragment : BaseFragment<FragmentMypageMyRestaurantBinding>(R.layout.fragment_mypage_my_restaurant) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    private val myRestaurantViewModel:MyRestaurantViewModel by viewModels()

    private lateinit var myRestaurantRvAdapter: MyRestaurantRvAdapter
    private var myRestaurantList = mutableListOf<MypageMyRestaurantItem>()
    private var currentPage = 0
    private var totalCount = 0
    private var collectJob: Job? = null
    override fun init() {
        setBackUp()
        setFabButton()

        reset()
        setRvAdapter()
    }
    private fun reset(){
        collectJob?.cancel()
        myRestaurantList = mutableListOf()
        currentPage = 0
        totalCount = 0
        myRestaurantViewModel.reSetVieModel()
    }
    private fun setRvAdapter(){
        myRestaurantRvAdapter = MyRestaurantRvAdapter(requireContext(), myRestaurantList)
        binding.rvMyRestaurant.adapter = myRestaurantRvAdapter
        binding.rvMyRestaurant.layoutManager = LinearLayoutManager(this.context)

        myRestaurantRvAdapter.setOnItemClickListener(object : MyRestaurantRvAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                //Magazine Detail로 이동

            }
        })

        setListener()
        myRestaurantViewModel.setMyRestaurantList(myRestaurantList)
        getMyRestaurantList()
    }
    private fun getMyRestaurantList(){
        myRestaurantViewModel.getMyRestaurant(currentPage)
        currentPage++
    }
    private fun setListener(){
        //page
        binding.rvMyRestaurant.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val rvPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                totalCount = recyclerView.adapter?.itemCount?.minus(1)!!
                if(rvPosition == totalCount && myRestaurantViewModel.isContinueGetList.value!!){
                    getMyRestaurantList()
                }
            }
        })

        collectJob = lifecycleScope.launch {
            myRestaurantViewModel.myRestaurantState.collect{state->
                when(state){
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        myRestaurantList.addAll(state.data?.response!!)
                        myRestaurantRvAdapter.notifyItemRangeInserted(totalCount,state.data.response.size)
                    }
                    is NetworkResult.Error -> {}
                }
            }
        }

        myRestaurantViewModel.isRestaurantEmpty.observe(this){
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
            binding.rvMyRestaurant.smoothScrollToPosition(0)
        }
    }
    private fun setEmptyState(emptyState:Boolean){
        binding.llEmptyArea.isVisible = emptyState
        binding.btnMoveToMap.setOnClickListener {
            mainNavigationHandler.navigateMyRestaurantToMap()
        }
    }
}