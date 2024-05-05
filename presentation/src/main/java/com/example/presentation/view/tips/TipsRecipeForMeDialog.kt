package com.example.presentation.view.tips

import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogWithTitleBinding

class TipsRecipeForMeDialog:BaseDialogFragment<DialogWithTitleBinding>(R.layout.dialog_with_title) {

    override fun init() {
        isCancelable = false

        binding.title = getString(R.string.dialog_tips_recipe_for_me_title)
        binding.content = getString(R.string.dialog_tips_recipe_for_me_description)
        binding.btnConfirm.text = getString(R.string.btn_confirm)

        binding.tvSubContent.visibility = ViewGroup.GONE
        binding.btnCancel.visibility = ViewGroup.GONE

        binding.btnConfirm.setOnClickListener {
            dismiss()
        }
    }
}