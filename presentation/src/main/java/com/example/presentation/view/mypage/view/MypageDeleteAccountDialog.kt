package com.example.presentation.view.mypage.view

import androidx.core.view.isVisible
import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogWithTitleBinding

class MypageDeleteAccountDialog:BaseDialogFragment<DialogWithTitleBinding>(R.layout.dialog_with_title) {
    private var listener: OnBtnClickListener? = null

    override fun init() {
//        isCancelable =false

        binding.title = getString(R.string.dialog_mypage_setting_delete_account_title)
        binding.content = getString(R.string.dialog_mypage_setting_delete_account_description)
        binding.tvSubContent.isVisible = false
        binding.btnCancel.text = getString(R.string.btn_cancel)
        binding.btnConfirm.text = getString(R.string.btn_delete_account)

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        binding.btnConfirm.setOnClickListener {
            listener?.onConfirm()
        }
    }

    interface OnBtnClickListener{
        fun onConfirm()
    }
    fun setOnConfirm(listener: OnBtnClickListener){
        this.listener = listener
    }
}