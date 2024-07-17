package com.example.presentation.view.map.view

import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainMapSearchListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VeganMapSearchFragment :
    BaseFragment<FragmentMainMapSearchListBinding>(R.layout.fragment_main_map_search_list) {
    override fun init() {
        binding.svSearch.isIconified = true
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                logMessage("submit $query")
                findNavController().navigate(R.id.action_veganMapSearchFragment_to_veganMapResultFragment)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                logMessage("text change $newText")
                return true
            }

        })
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().popBackStack()
        }
    }
}