package com.example.presentation.view.gallery.view

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentGallerySelectImageBinding
import com.example.presentation.view.gallery.viewModel.GalleryViewModel

class SelectImageFragment : BaseFragment<FragmentGallerySelectImageBinding>(R.layout.fragment_gallery_select_image){
//    private val args: SelectImageFragmentArgs by navArgs()

    private val viewModel: GalleryViewModel by activityViewModels()
    override fun init() {
        viewModel.selectImage.value?.let { selectedImage ->
            binding.clCropper.setUri(selectedImage.imageUri)
        }
    }
}