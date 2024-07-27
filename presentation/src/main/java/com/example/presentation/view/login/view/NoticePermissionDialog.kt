package com.example.presentation.view.login.view

import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogNoticePermissionBinding
import com.example.presentation.databinding.DialogPermissionBinding

class NoticePermissionDialog: BaseDialogFragment<DialogNoticePermissionBinding>(R.layout.dialog_notice_permission) {
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