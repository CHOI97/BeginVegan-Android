package com.example.presentation.view.mypage.view

import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MypageEditProfileFragment : BaseFragment<FragmentMypageEditProfileBinding>(R.layout.fragment_mypage_edit_profile) {
    override fun init() {

        binding.ibBackUp.setOnClickListener {
//            requireActivity().supportFragmentManager.popBackStack()
            findNavController().popBackStack()
        }
    }
}