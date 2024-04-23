package com.example.presentation.view.mypage

import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainMypageBinding


////import com.bumptech.glide.Glide
//import com.example.beginvegan.R
//import com.example.beginvegan.config.ApplicationClass
//import com.example.beginvegan.config.BaseFragment
//import com.example.beginvegan.databinding.FragmentMainMypageBinding
//import com.example.beginvegan.util.LogoutDialog
//import com.example.beginvegan.util.ProfileEditNameDialog
//import com.example.beginvegan.util.ProfileEditVeganTypeDialog
//import com.example.presentation.base.BaseFragment
//
//class MainMypageFragment : BaseFragment<FragmentMainMypageBinding>(
//    FragmentMainMypageBinding::bind, R.layout.fragment_main_mypage
//), BottomSheetLogoutFragment.MyFragmentInteractionListener,
//    ProfileEditNameDialog.EditNameDialogListener,
//    ProfileEditVeganTypeDialog.EditVeganTypeDialogListener {
//    override fun init() {
////
////        //ViewPager
////        val vpMyRecords = binding.vpMyRecords
////        vpMyRecords.adapter = ProfileMyRecordsVPAdapter(this)
////        //tab
////        val tabLayout = binding.tlRecords
////        TabLayoutMediator(tabLayout, vpMyRecords) { tab, pos ->
////            when (pos) {
////                0 -> tab.text = "나의 리뷰"
////                1 -> tab.text = "나의 스크랩"
////            }
////        }.attach()
////        //시작 시 유저 이름 반영
////        binding.tvUsername.text = ApplicationClass.xAuth.name
////
////        //닉네임 수정 dialog
////        binding.ibEditUsername.setOnClickListener {
////            openEditUserNameDialog()
////        }
////        //Vegan Type 수정 dialog
////        binding.ibEditVeganType.setOnClickListener {
////            openEditVeganTypeDialog()
////        }
////        //테스트 바로가기
////        binding.bGoVeganTest.setOnClickListener {
////            val intent = Intent(this.context, VeganTestActivity::class.java)
////            startActivity(intent)
////        }
////        //로그아웃 more button 클릭
////        binding.btnProfileMore.setOnClickListener {
////            openBottomSheetLogout()
////        }
////        binding.tvUsername.text = ApplicationClass.xAuth.name
//////        binding.tvUserVeganType.text = VeganTypes.valueOf("${ApplicationClass.xAuth.veganType}").veganType
////        if (ApplicationClass.xAuth.imageUrl != null) {
////            Glide.with(requireContext()).load(ApplicationClass.xAuth.imageUrl)
////                .into(binding.civProfileImage)
////        }
//    }
////
//    //닉네임 수정
////    private fun openEditUserNameDialog() { //dialog 띄우기
////        val editNameDialog =
////            ProfileEditNameDialog(requireContext(), binding.tvUsername.text.toString())
////        editNameDialog.setListener(this)
////        editNameDialog.show()
////    }
//
//    override fun editNameOnSaveClicked(name: String) { //수정한 name UI 반영
//        ApplicationClass.xAuth.name = name //이름 변경
////        binding.tvUsername.text = name
//    }
////
//    //비건 유형 수정
////    private fun openEditVeganTypeDialog() { //dialog 띄우기
////        val editVeganTypeDialog =
////            ProfileEditVeganTypeDialog(requireContext(), binding.tvUserVeganType.text.toString())
////        editVeganTypeDialog.setListener(this)
////        editVeganTypeDialog.show()
////    }
////
//    override fun editVeganTypeOnSaveClicked(type: String) {
////        binding.tvUserVeganType.text = type
//    }
//
////    //로그아웃
////    private fun openBottomSheetLogout() { //bottom sheet 열기
////        val bottomSheet = BottomSheetLogoutFragment()
////        bottomSheet.listener = this
////        bottomSheet.show(requireActivity().supportFragmentManager, null)
////    }
//
//        fun openLogoutDialog() { //dialog 띄우기
//            val logoutDialog = LogoutDialog(requireContext())
//            logoutDialog.show()
//        }
//
//    override fun onButtonClicked() { //dialog 로그아웃 버튼 클릭
//        openLogoutDialog()
//    }
//}

class MainMypageFragment : BaseFragment<FragmentMainMypageBinding>{
    override fun init() {

    }

}