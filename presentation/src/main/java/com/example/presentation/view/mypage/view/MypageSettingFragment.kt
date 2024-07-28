package com.example.presentation.view.mypage.view

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMypageSettingBinding
import com.example.presentation.view.mypage.viewModel.MypagePushViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MypageSettingFragment : BaseFragment<FragmentMypageSettingBinding>(R.layout.fragment_mypage_setting) {
    private val mypagePushViewModel: MypagePushViewModel by viewModels()

    override fun init() {
        binding.lifecycleOwner = this

        setBackUp()
        setPushToggle()
        setLogOut()
        setDeleteAccount()
    }

    //Push 알림 설정
    private fun setPushToggle(){
        mypagePushViewModel.getPushState()

        mypagePushViewModel.userPushState.observe(this){
            binding.scPushSwitch.setOnCheckedChangeListener(null)
            binding.scPushSwitch.isChecked = it

            //Patch
            binding.scPushSwitch.setOnCheckedChangeListener { _, isChecked ->
                mypagePushViewModel.patchPush()
                if(!isChecked){
                    //Dialog 처리
                }
            }
        }
    }

    //backStack
    private fun setBackUp(){
        binding.ibBackUp.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    //로그아웃
    private fun setLogOut(){
        binding.tvLogout.onThrottleClick{
            openDialogLogout()
        }
    }
    private fun openDialogLogout(){
        MypageLogoutDialog().show(childFragmentManager, "LogoutDialog")
        MypageLogoutDialog().setOnConfirm(object : MypageLogoutDialog.OnBtnClickListener {
            override fun onConfirm() {
                //확인 클릭 시
            }
        })
    }

    //계정삭제
    private fun setDeleteAccount(){
        binding.tvDeleteAccount.onThrottleClick {
            openDialogDeleteAccount()
        }
    }
    private fun openDialogDeleteAccount(){
        MypageDeleteAccountDialog().show(childFragmentManager, "DeleteAccountDialog")
        MypageDeleteAccountDialog().setOnConfirm(object :
            MypageDeleteAccountDialog.OnBtnClickListener {
            override fun onConfirm() {
                //계정 삭제
            }

        })
    }
}