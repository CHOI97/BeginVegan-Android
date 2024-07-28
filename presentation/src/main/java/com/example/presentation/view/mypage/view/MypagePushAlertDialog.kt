package com.example.presentation.view.mypage.view

import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogPermissionRefuseNotificationBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MypagePushAlertDialog(private val permit:Boolean):BaseDialogFragment<DialogPermissionRefuseNotificationBinding>(R.layout.dialog_permission_refuse_notification) {
    override fun init() {
        if(permit) setBinding("동의", "거부")
        else setBinding("거부", "동의")

        binding.btnConfirm.setOnClickListener {
            dismiss()
        }
    }
    private fun setBinding(type: String, noType:String){
        binding.tvTitle.text = getString(R.string.permission_refuse_notification_title, type)
        binding.tvContentContent.text = getString(R.string.permission_refuse_notification_content, type)
        binding.tvSubContent.text = getString(R.string.permission_refuse_notification_description, noType)

        val currentDate = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        binding.tvDateContent.text = currentDate.format(formatter)
    }
}