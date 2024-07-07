package com.example.presentation.view.gallery.view

import android.view.View
import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogPhotoSelectBinding

class PhotoSelectDialog(private val isProfileImage: Boolean): BaseDialogFragment<DialogPhotoSelectBinding>(R.layout.dialog_photo_select) {
    interface DialogPhotoSelectClickListener{
        fun onClickCamera()
        fun onClickGallery()

        fun onClickDefault()
    }

    private var listener: DialogPhotoSelectClickListener? = null

    override fun init() {
        hasProfileImage()
        binding.btnGallery.setOnClickListener {

        }
    }
    private fun onCamera(){
        listener?.onClickCamera()
    }
    private fun onGallery(){
        listener?.onClickGallery()
    }
    private fun onDefault(){
        listener?.onClickDefault()
    }

    private fun hasProfileImage(){
        if(isProfileImage){
            binding.btnDefault.visibility = View.VISIBLE
        }else{
            binding.btnDefault.visibility = View.GONE
        }
    }

    fun setDialogClickListener(listener: DialogPhotoSelectClickListener){
        this.listener = listener
    }
}