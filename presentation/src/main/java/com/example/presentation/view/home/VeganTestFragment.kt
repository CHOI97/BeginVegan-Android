package com.example.presentation.view.home

import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.main.MainNavigationHandler
import com.example.presentation.databinding.FragmentVeganTestBinding
import com.example.presentation.databinding.IncludeIllusVeganLevelBinding
import com.example.presentation.view.home.viewModel.VeganTestViewModel
import com.example.presentation.view.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VeganTestFragment : BaseFragment<FragmentVeganTestBinding>(R.layout.fragment_vegan_test) {
    private var latestRadioButton:View? = null
    private var latestIncludedView: IncludeIllusVeganLevelBinding? =null

    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    private val viewModel: VeganTestViewModel by activityViewModels()

    private var selectedVeganType = ""
    private lateinit var veganTypes:Array<String>
    private lateinit var veganTestDescriptions:Array<String>

    override fun init() {

        binding.includedToolbar.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        veganTypes = resources.getStringArray(R.array.vegan_types_eng)
        veganTestDescriptions = resources.getStringArray(R.array.vegan_test_descriptions)

        setDescription(binding.includedVegan,binding.acrbLacto, veganTestDescriptions[0], veganTypes[1])
        controlRadioButton()

        binding.tvGoResult.setOnClickListener {
            goResult()
        }
    }
    private fun controlRadioButton(){
        binding.rgVeganTest.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.acrb_vegan -> setDescription(binding.includedVegan,binding.acrbLacto, veganTestDescriptions[0], veganTypes[1])
                R.id.acrb_lacto -> setDescription(binding.includedLacto,binding.acrbOvo, veganTestDescriptions[1], veganTypes[2])
                R.id.acrb_ovo -> setDescription(binding.includedOvo,binding.acrbLactoOvo, veganTestDescriptions[2],veganTypes[3])
                R.id.acrb_lacto_ovo -> setDescription(binding.includedLactoOvo,binding.acrbPesco, veganTestDescriptions[3],veganTypes[4])
                R.id.acrb_pesco -> setDescription(binding.includedPesco, binding.acrbPollo,veganTestDescriptions[4],veganTypes[5])
                R.id.acrb_pollo -> setDescription(binding.includedPollo, binding.acrbFlexitarian,veganTestDescriptions[5],veganTypes[6])
                R.id.acrb_flexitarian -> setDescription(binding.includedFlexitarian,null,veganTestDescriptions[6],veganTypes[7])
            }
        }
    }
    private fun goResult(){
        viewModel.setUserVeganType(selectedVeganType)
        mainNavigationHandler.navigateToBeganTestResult()
    }

    //라디오버튼 클릭 시 반영
    private fun setDescription(selectedView: IncludeIllusVeganLevelBinding, radioView: View?,selectedDescription: String, veganType:String){
        resetMargin(latestRadioButton,94)
        latestIncludedView?.tvDescription?.isVisible = false

        selectedView.description = selectedDescription
        selectedView.tvDescription.isVisible = true
        resetMargin(radioView, 160)

        latestIncludedView = selectedView
        latestRadioButton = radioView ?: binding.acrbFlexitarian

        selectedVeganType = veganType
    }
    private fun resetMargin(radioView:View?, marginTop:Int){
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, marginTop, 0, 0)
        radioView?.layoutParams = params
    }
}