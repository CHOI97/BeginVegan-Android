package com.example.presentation.view.tips

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TipsVpAdapter(fragmentManager:FragmentManager, lifecycle:Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle) {
    private val TAB_COUNT = 2
    override fun getItemCount(): Int = TAB_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TipsMagazineFragment()
            1 -> TipsRecipeFragment()
            else -> TipsMagazineFragment()
        }
    }
}