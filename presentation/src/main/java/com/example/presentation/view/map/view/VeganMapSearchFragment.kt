package com.example.presentation.view.map.view

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.map.HistorySearch
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMapSearchBinding
import com.example.presentation.view.map.adapter.VeganMapSearchRVAdapter
import com.example.presentation.view.map.viewModel.VeganMapSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VeganMapSearchFragment :
    BaseFragment<FragmentMapSearchBinding>(R.layout.fragment_map_search) {

    private val viewModel: VeganMapSearchViewModel by viewModels()
    private lateinit var veganMapSearchRVAdapter: VeganMapSearchRVAdapter
    override fun init() {

        initHistory()

        showKeyBoard()

        onSearch()

        onAllDelete()

        setHistorySearchRVAdapter()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

    }

    private fun onAllDelete() {
        binding.btnAllDelete.setOnClickListener {
            viewModel.deleteAllHistory()
        }

    }

    private fun initHistory() {
        viewModel.fetchAllHistory()
    }

    private fun setHistorySearchRVAdapter() {

        veganMapSearchRVAdapter = VeganMapSearchRVAdapter()

        binding.rvSearch.adapter = veganMapSearchRVAdapter

        logMessage("setHistorySearchRVAdapter value = ${viewModel.searchList.value}")
        veganMapSearchRVAdapter.submitList(viewModel.searchList.value)

        veganMapSearchRVAdapter.setOnItemClickListener(object :
            VeganMapSearchRVAdapter.OnDeleteListener {
            override fun onDelete(data: HistorySearch) {
                viewModel.deleteHistory(data)
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchList.collect() {
                if (it.isEmpty()) {
                    logMessage("search list empty")
                    binding.tvEmptyView.visibility = View.VISIBLE
                    binding.btnAllDelete.visibility = View.GONE
                    binding.rvSearch.visibility = View.INVISIBLE
                    veganMapSearchRVAdapter.submitList(viewModel.searchList.value)
                } else {
                    logMessage("search list not empty")
                    binding.rvSearch.visibility = View.VISIBLE
                    binding.tvEmptyView.visibility = View.GONE
                    binding.btnAllDelete.visibility = View.VISIBLE
                    veganMapSearchRVAdapter.submitList(viewModel.searchList.value)
                }
            }
        }
    }

    private fun onSearch() {
        binding.tieSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.insertHistory(v.text.toString())
                navigateSearchResult(v.text.toString())
                true
            } else {
                false

            }
        }
    }

    private fun navigateSearchResult(args: String) {
        findNavController().navigate(
            VeganMapSearchFragmentDirections.actionVeganMapSearchFragmentToVeganMapResultFragment(
                args
            )
        )

    }

    private fun showKeyBoard() {
        binding.tieSearch.requestFocus()
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.tieSearch, InputMethodManager.SHOW_IMPLICIT)
    }

}