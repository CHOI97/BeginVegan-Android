//package com.example.presentation.view.main
//
//import android.os.Bundle
//import android.util.Log
//import com.example.presentation.base.BaseActivity
//import com.example.presentation.databinding.ActivityMainBinding
//
//class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it)}) {
//    override fun init() {
//        supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainHomeFragment()).commit()
//        setBottomNavigationViewListener()
//    }
//    private fun setBottomNavigationViewListener(){
//
//        binding.bnvMain.setOnItemSelectedListener {
//            when(it.itemId){
//                R.id.item_home->{
//                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,MainHomeFragment()).commit()
//                }
//                R.id.item_map->{
//                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,
//                        VeganMapFragment()
//                    ).setReorderingAllowed(true).commitAllowingStateLoss()
//                }
//                R.id.item_recipe->{
//                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,
//                        MainRecipeFragment()
//                    ).commit()
//                }
//                R.id.item_profile->{
//                    supportFragmentManager.beginTransaction().replace(R.id.fl_main,
//                        MainProfileFragment()
//                    ).commit()
//                }
//            }
//            true
//        }
//    }
//    fun setActiveBottomNavigationItem(itemId: Int) {
//        binding.bnvMain.setOnItemSelectedListener(null)
//        binding.bnvMain.selectedItemId = itemId
//        setBottomNavigationViewListener()
//    }
//}