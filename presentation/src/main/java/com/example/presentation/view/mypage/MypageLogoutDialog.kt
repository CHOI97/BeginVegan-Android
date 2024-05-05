package com.example.presentation.view.mypage

import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogWithoutTitleBinding

class MypageLogoutDialog:BaseDialogFragment<DialogWithoutTitleBinding>(R.layout.dialog_without_title){
    private var listener: OnBtnClickListener? = null

    override fun init() {
        isCancelable =false

        binding.content = getString(R.string.dialog_mypage_setting_logout)
        binding.btnCancel.text = getString(R.string.btn_cancel)
        binding.btnConfirm.text = getString(R.string.btn_confirm)

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