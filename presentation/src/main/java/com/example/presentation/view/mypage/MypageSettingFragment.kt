package com.example.presentation.view.mypage

import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageSettingBinding
import com.example.presentation.view.main.MainFragment

class MypageSettingFragment : BaseFragment<FragmentMypageSettingBinding>(R.layout.fragment_mypage_setting) {
    override fun init() {
        binding.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        //로그아웃
        binding.tvLogout.onThrottleClick{
            openDialogLogout()
        }

        //계정 삭제
        binding.tvDeleteAccount.onThrottleClick {
            openDialogDeleteAccount()
        }
    }

    private fun openDialogLogout(){
        MypageLogoutDialog().show(childFragmentManager, "LogoutDialog")
        MypageLogoutDialog().setOnConfirm(object :MypageLogoutDialog.OnBtnClickListener{
            override fun onConfirm() {
                //확인 클릭 시
            }
        })
    }

    private fun openDialogDeleteAccount(){
        MypageDeleteAccountDialog().show(childFragmentManager, "DeleteAccountDialog")
        MypageDeleteAccountDialog().setOnConfirm(object :MypageDeleteAccountDialog.OnBtnClickListener{
            override fun onConfirm() {
                //계정 삭제
            }

        })
    }
}