package com.example.presentation.util

import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogLoadingBinding

class LoadingDialog: BaseDialogFragment<DialogLoadingBinding>(R.layout.dialog_loading) {
    override fun init() {
        setDim(0.7f)
        setCancelAble(false)
    }

}