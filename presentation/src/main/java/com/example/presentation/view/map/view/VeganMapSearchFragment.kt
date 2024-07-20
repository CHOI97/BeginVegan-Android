package com.example.presentation.view.map.view

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMapSearchBinding
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VeganMapSearchFragment :
    BaseFragment<FragmentMapSearchBinding>(R.layout.fragment_map_search) {
    override fun init() {

        showKeyBoard()
        onSearch()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

    }

    private fun onSearch() {
        binding.tieSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                showToast("검색")
                navigateSearchResult(v.text.toString())
                true
            } else {
                false

            }
        }
    }

    private fun navigateSearchResult(args: String) {
        findNavController().navigate(VeganMapSearchFragmentDirections.actionVeganMapSearchFragmentToVeganMapResultFragment(args))

    }

    private fun showKeyBoard() {
        binding.tieSearch.requestFocus()
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.tieSearch, InputMethodManager.SHOW_IMPLICIT)
    }

}