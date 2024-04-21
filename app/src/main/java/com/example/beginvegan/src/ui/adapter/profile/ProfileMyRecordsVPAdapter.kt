//package com.example.beginvegan.src.ui.adapter.profile
//
//import android.util.Log
//import androidx.fragment.app.Fragment
//import androidx.viewpager2.adapter.FragmentStateAdapter
//import com.example.beginvegan.src.ui.view.mypage.MainMypageFragment
//import com.example.beginvegan.src.ui.view.mypage.MypageMyreviewFragment
//import com.example.beginvegan.src.ui.view.mypage.MypageMyscrapFragment
//
//class ProfileMyRecordsVPAdapter(mainProfileFragment: MainMypageFragment): FragmentStateAdapter(mainProfileFragment) {
//
//    val fragmentList = listOf<Fragment>(
//        MypageMyreviewFragment(),
//        MypageMyscrapFragment()
//    )
//
//    override fun getItemCount(): Int { return fragmentList.size }
//
//    override fun createFragment(position: Int): Fragment {
//        Log.d("TAG", "createFragment: adapter")
//        return when (position) {
//            0 -> MypageMyreviewFragment()
//            else -> {
//                MypageMyscrapFragment()
//            }
//        }
//    }
//}