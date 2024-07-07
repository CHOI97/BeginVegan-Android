package com.example.presentation.util

import android.view.View
import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogPhotoSelectBinding

class PhotoSelectDialog(private val isProfileImage: Boolean): BaseDialogFragment<DialogPhotoSelectBinding>(R.layout.dialog_photo_select) {
    override fun init() {
        hasProfileImage()
    }

    private fun hasProfileImage(){
        if(isProfileImage){
            binding.btnDefault.visibility = View.VISIBLE
        }else{
            binding.btnDefault.visibility = View.GONE
        }
    }
}