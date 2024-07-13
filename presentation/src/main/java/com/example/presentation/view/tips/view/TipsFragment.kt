package com.example.presentation.view.tips.view

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentMainTipsBinding
import com.example.presentation.util.DrawerController
import com.example.presentation.view.tips.adapter.TipsVpAdapter
import com.example.presentation.view.tips.viewModel.RecipeViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class TipsFragment: BaseFragment<FragmentMainTipsBinding>(R.layout.fragment_main_tips) {
    @Inject
    lateinit var drawerController: DrawerController
    private val recipeViewModel: RecipeViewModel by activityViewModels()
    override fun init() {
        binding.lifecycleOwner = this

        setTipsTab()
    }
    private fun setTipsTab() {
        binding.vpViewpagerArea.adapter = TipsVpAdapter(childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tlTab, binding.vpViewpagerArea){ tab, position ->
            when(position){
                0 -> tab.text = getString(R.string.magazine)
                1 -> tab.text = getString(R.string.recipe)
            }
        }.attach()

        checkFromTest()
        setOpenDrawer()
    }

    //이동
    private fun setOpenDrawer() {
        binding.includedToolbar.ibNotification.setOnClickListener {
            drawerController.openDrawer()
        }
    }

    private fun checkFromTest(){
        val args: TipsFragmentArgs by navArgs()
        Timber.d("args.fromTest:${args.fromTest}, args.fromMyRecipe:${args.fromMyRecipe}")
        if(args.fromTest){
            //나를 위한 레시피
            binding.vpViewpagerArea.post{
                binding.vpViewpagerArea.currentItem = 1
                recipeViewModel.setIsFromTest(true)
            }
        }
        if(args.fromMyRecipe){
            binding.vpViewpagerArea.post{
                binding.vpViewpagerArea.currentItem = 1
            }
        }
    }
}