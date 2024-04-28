package com.example.presentation.view.login

import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogPermissionBinding

class PermissionDialog: BaseDialogFragment<DialogPermissionBinding>(R.layout.dialog_permission) {
    override fun init() {
        setOnClickOk()
    }

    private fun setOnClickOk() {
        binding.btnPermissionDialogOk.setOnClickListener {
            this.dismiss()

            // OS 12 이하는 Notification Permission 추가하기
        }
    }
}