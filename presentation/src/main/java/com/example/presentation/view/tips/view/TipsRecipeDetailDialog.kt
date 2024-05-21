package com.example.presentation.view.tips.view

import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogRecipeDetailBinding

class TipsRecipeDetailDialog:BaseDialogFragment<DialogRecipeDetailBinding>(R.layout.dialog_recipe_detail) {
    override fun init() {
        isCancelable = false

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}