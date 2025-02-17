package com.example.presentation.view.home.veganTest.view

import androidx.fragment.app.activityViewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.FragmentVeganTestResultBinding
import com.example.presentation.view.home.veganTest.viewModel.VeganTestViewModel
import com.example.presentation.view.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class VeganTestResultFragment : BaseFragment<FragmentVeganTestResultBinding>(R.layout.fragment_vegan_test_result) {

    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    private val viewModel: VeganTestViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by hiltNavGraphViewModels(R.id.nav_main_graph)

    private lateinit var veganTypes:Array<String>
    private lateinit var resultDescriptions:Array<String>
    private lateinit var resultExplanations:Array<String>
    private var typeNum = 0

    override fun init() {
        binding.lifecycleOwner = this

        veganTypes = resources.getStringArray(R.array.vegan_type)
        resultDescriptions = resources.getStringArray(R.array.vegan_test_result_descriptions)
        resultExplanations = resources.getStringArray(R.array.vegan_test_result_explanations)

        viewModel.userVeganTypeNum.observe(this, Observer {
            typeNum = viewModel.userVeganTypeNum.value!!
            binding.tvResultVeganType.text = veganTypes[typeNum+1]
            binding.tvVeganTypeDescription.text = resultDescriptions[typeNum]
            binding.tvResultExplanation.text = resultExplanations[typeNum]
            setIllus()
        })

//        binding.tvDescription.text = 유저네임
//        binding.tvGoRecommendRecipe.text = 유저 네임

        goBackUp()
        goRecommendRecipe()
    }

    private fun setIllus(){
        val levels = listOf(
            { binding.includedIllusVeganLevel.milk = true },
            { binding.includedIllusVeganLevel.egg = true },
            { binding.includedIllusVeganLevel.fish = true },
            { binding.includedIllusVeganLevel.chicken = true },
            { binding.includedIllusVeganLevel.meat = true }
        )
        when(typeNum){
            0 -> return
            1 -> levels[0]()
            2 -> levels[1]()
            else -> {
                for (i in 0 until typeNum-1) {
                    levels[i]()
                }
            }
        }
    }

    //이동
    private fun goBackUp(){
        binding.includedToolbar.ibBackUp.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun goRecommendRecipe(){
        binding.tvBtnGoRecommendRecipe.setOnClickListener {
            mainViewModel.setFromTest(true)
            mainNavigationHandler.navigateTestResultToTips()
        }
    }
}