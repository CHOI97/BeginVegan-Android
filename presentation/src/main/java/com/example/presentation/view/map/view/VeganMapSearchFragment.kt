package com.example.presentation.view.map.view

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.map.HistorySearch
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMapSearchBinding
import com.example.presentation.view.map.adpater.VeganMapSearchRVAdapter
import com.example.presentation.view.map.viewModel.VeganMapSearchViewModel
import com.example.presentation.view.map.viewModel.VeganMapViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class VeganMapSearchFragment :
    BaseFragment<FragmentMapSearchBinding>(R.layout.fragment_map_search) {

    private val viewModel: VeganMapSearchViewModel by viewModels()
    private lateinit var veganMapSearchRVAdapter: VeganMapSearchRVAdapter
    override fun init() {

        showKeyBoard()

        onSearch()

        setHistorySearchRVAdapter()

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchList.collect() {
                if (it.isEmpty()) {
                    viewModel.updateAllDeleteState(false)
                } else {
                    viewModel.updateAllDeleteState(true)
                }
            }
        }
        viewModel.allDeleteState.observe(viewLifecycleOwner) {
            if (it) {
                binding.btnAllDelete.visibility = View.VISIBLE
            } else {
                binding.btnAllDelete.visibility = View.GONE
            }
        }
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