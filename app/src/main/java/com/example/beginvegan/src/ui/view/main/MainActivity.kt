//package com.example.beginvegan.src.ui.view.main
//
//import com.example.beginvegan.R
//import com.example.beginvegan.config.BaseActivity
//import com.example.beginvegan.databinding.ActivityMainBinding
//import com.example.beginvegan.src.ui.view.recipe.MainRecipeFragment
//import com.example.beginvegan.src.ui.view.home.MainHomeFragment
//import com.example.beginvegan.src.ui.view.map.VeganMapFragment
//import com.example.beginvegan.src.ui.view.mypage.MainMypageFragment
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
//                        MainMypageFragment()
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