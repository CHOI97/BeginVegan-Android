package com.example.presentation.view.home

import android.view.View
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentVeganTestBinding
import com.example.presentation.databinding.IncludeIllusVeganLevelBinding
import com.example.presentation.view.main.MainActivity

class VeganTestFragment : BaseFragment<FragmentVeganTestBinding>(R.layout.fragment_vegan_test) {
    private var latestRadioButton:View? = null
    private var latestIncludedView: IncludeIllusVeganLevelBinding? =null
    override fun init() {
        (activity as MainActivity).setStateToolBar(false)
        (activity as MainActivity).setStateBn(false)

        binding.includedToolbar.ibBackUp.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        setDescription(binding.includedVegan,binding.acrbLacto, getString(R.string.vegan_test_description_vegan))
        controlRadioButton()

        binding.tvGoResult.setOnClickListener {
            goResult()
        }
    }
    private fun controlRadioButton(){
        binding.rgVeganTest.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.acrb_vegan -> setDescription(binding.includedVegan,binding.acrbLacto, getString(R.string.vegan_test_description_vegan))
                R.id.acrb_lacto -> setDescription(binding.includedLacto,binding.acrbOvo, getString(R.string.vegan_test_description_lacto))
                R.id.acrb_ovo -> setDescription(binding.includedOvo,binding.acrbLactoOvo, getString(R.string.vegan_test_description_ovo))
                R.id.acrb_lacto_ovo -> setDescription(binding.includedLactoOvo,binding.acrbPesco, getString(R.string.vegan_test_description_lacto_ovo))
                R.id.acrb_pesco -> setDescription(binding.includedPesco, binding.acrbPollo,getString(R.string.vegan_test_description_pesco))
                R.id.acrb_pollo -> setDescription(binding.includedPollo, binding.acrbFlexitarian,getString(R.string.vegan_test_description_pollo))
                R.id.acrb_flexitarian -> setDescription(binding.includedFlexitarian,null, getString(R.string.vegan_test_description_flexitarian))
            }
        }
    }
    private fun goResult(){
        val checkedId = binding.rgVeganTest.checkedRadioButtonId
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcw_main, VeganTestResultFragment())
            .addToBackStack(null)
            .commit()
    }
    //라디오버튼 클릭 시 반영
    private fun setDescription(selectedView: IncludeIllusVeganLevelBinding, radioView: View?,selectedDescription: String){
        resetMargin(latestRadioButton,94)
        latestIncludedView?.tvDescription?.isVisible = false

        selectedView.description = selectedDescription
        selectedView.tvDescription.isVisible = true
        resetMargin(radioView, 160)

        latestIncludedView = selectedView
        latestRadioButton = radioView ?: binding.acrbFlexitarian
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