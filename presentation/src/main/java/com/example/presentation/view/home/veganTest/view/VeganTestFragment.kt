package com.example.presentation.view.home.veganTest.view

import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.FragmentVeganTestBinding
import com.example.presentation.databinding.IncludeIllusVeganLevelBinding
import com.example.presentation.view.home.veganTest.viewModel.VeganTestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class VeganTestFragment : BaseFragment<FragmentVeganTestBinding>(R.layout.fragment_vegan_test) {
    private val PATCH_TYPE = "TEST"

    private var latestRadioButton:View? = null
    private var latestUnderRadioButton:View? = null
    private var latestIncludedView: IncludeIllusVeganLevelBinding? =null

    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    private val viewModel: VeganTestViewModel by activityViewModels()

    private var selectedVeganTypeNum = 0
    private lateinit var veganTestDescriptions:Array<String>
    private lateinit var veganTypes:Array<String>

    private var patchJob:Job? = null

    override fun init() {
        binding.lifecycleOwner = this
        patchJob?.cancel()

        veganTestDescriptions = resources.getStringArray(R.array.vegan_test_descriptions)
        veganTypes = resources.getStringArray(R.array.vegan_types_eng)

        setDescription(binding.includedVegan,null,binding.acrbLacto,0 )
        controlRadioButton()

        goBackUp()
        goResult()
    }
    private fun controlRadioButton(){
        binding.rgVeganTest.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.acrb_vegan -> setDescription(binding.includedVegan,null,binding.acrbLacto, 0)
                R.id.acrb_lacto -> setDescription(binding.includedLacto,binding.acrbLacto,binding.acrbOvo, 1)
                R.id.acrb_ovo -> setDescription(binding.includedOvo,binding.acrbOvo, binding.acrbLactoOvo,2)
                R.id.acrb_lacto_ovo -> setDescription(binding.includedLactoOvo,binding.acrbLactoOvo, binding.acrbPesco,3)
                R.id.acrb_pesco -> setDescription(binding.includedPesco, binding.acrbPesco, binding.acrbPollo,4)
                R.id.acrb_pollo -> setDescription(binding.includedPollo, binding.acrbPollo, binding.acrbFlexitarian,5)
                R.id.acrb_flexitarian -> setDescription(binding.includedFlexitarian, binding.acrbFlexitarian,null,6)
            }
        }

        //일러스트 영역 클릭 시 토글 반영
        binding.includedVegan.llLayout.setOnClickListener {
            binding.rgVeganTest.check(R.id.acrb_vegan) }
        binding.includedLacto.llLayout.setOnClickListener {
            binding.rgVeganTest.check(R.id.acrb_lacto) }
        binding.includedOvo.llLayout.setOnClickListener {
            binding.rgVeganTest.check(R.id.acrb_ovo) }
        binding.includedLactoOvo.llLayout.setOnClickListener {
            binding.rgVeganTest.check(R.id.acrb_lacto_ovo) }
        binding.includedPesco.llLayout.setOnClickListener {
            binding.rgVeganTest.check(R.id.acrb_pesco) }
        binding.includedPollo.llLayout.setOnClickListener {
            binding.rgVeganTest.check(R.id.acrb_pollo) }
        binding.includedFlexitarian.llLayout.setOnClickListener {
            binding.rgVeganTest.check(R.id.acrb_flexitarian) }
    }

    //라디오버튼 클릭 시 반영
    private fun setDescription(selectedView: IncludeIllusVeganLevelBinding, selectedRadioView: View?, underRadioView:View?, veganTypeNum:Int){
        setMargin(latestRadioButton, 26)
        setMargin(latestUnderRadioButton, 26)
        latestIncludedView?.tvDescription?.isVisible = false

        selectedView.description = veganTestDescriptions[veganTypeNum]
        selectedView.tvDescription.isVisible = true

        if(underRadioView==null) setMargin(selectedRadioView, 39)
        else setMargin(selectedRadioView, 33)

        if(selectedRadioView==null) setMargin(underRadioView, 40)
        else setMargin(underRadioView, 33)

        latestIncludedView = selectedView
        latestRadioButton = selectedRadioView
        latestUnderRadioButton = underRadioView ?: binding.acrbFlexitarian

        selectedVeganTypeNum = veganTypeNum
    }
    private fun setMargin(radioView:View?, marginTop:Int){
        val density = resources.displayMetrics.density
        val marginTop = (marginTop * density).toInt()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, marginTop, 0, 0)
        radioView?.layoutParams = params
    }

    //이동
    private fun goBackUp(){
        binding.includedToolbar.ibBackUp.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun goResult(){
        binding.tvGoResult.setOnClickListener {
            viewModel.setUserVeganTypeNum(selectedVeganTypeNum)

            patchVeganType(selectedVeganTypeNum)
            patchJob = lifecycleScope.launch {
                viewModel.patchVeganTypeState.collect{
                    when (it) {
                        true -> {
                            Timber.d("PatchVeganType Successful")
                            mainNavigationHandler.navigateTestToVeganTestResult()
                        }
                        false -> Timber.e("PatchVeganType Failure")
                    }
                }
            }
        }
    }

    //retrofit
    private fun patchVeganType(typeNum: Int){
        viewModel.patchVeganType(PATCH_TYPE,veganTypes[typeNum+1])
    }
}