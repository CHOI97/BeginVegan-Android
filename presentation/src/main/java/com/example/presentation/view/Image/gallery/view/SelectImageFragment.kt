package com.example.presentation.view.Image.gallery.view

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import androidx.appcompat.widget.SearchView.OnCloseListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentGallerySelectImageBinding
import com.example.presentation.view.Image.gallery.model.GalleryImage
import com.example.presentation.view.Image.gallery.viewModel.GalleryViewModel
import com.takusemba.cropme.OnCropListener
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class SelectImageFragment :
    BaseFragment<FragmentGallerySelectImageBinding>(R.layout.fragment_gallery_select_image) {

    private val viewModel: GalleryViewModel by activityViewModels()
    override fun init() {
        viewModel.selectImage.value?.let { selectedImage ->
            selectedImage.imageUri?.let { binding.clCropper.setUri(it) }
        }
        binding.ibDone.setOnClickListener {
            binding.clCropper.crop()
        }
        binding.clCropper.addOnCropListener(object : OnCropListener {
            override fun onFailure(e: Exception) {
                Timber.d("사진 크롭 실패")
            }

            override fun onSuccess(bitmap: Bitmap) {
                val path = bitmapToFilePath(bitmap)
                Timber.d(
                    "사진 크롭 성공\n" +
                            "$bitmap\n" +
                            "$path\n"
                )

                viewModel.updateResultImage(GalleryImage(Uri.fromFile(File(path)), false, path))
            }

        })

    }

    private fun bitmapToFilePath(inImage: Bitmap): String? {
        val imageFile =
            File(requireContext().cacheDir, "cropped_image_${System.currentTimeMillis()}.jpg")
        imageFile.createNewFile()
        val bos = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        val bitmapData = bos.toByteArray()

        val fos = FileOutputStream(imageFile)
        fos.write(bitmapData)
        fos.flush()
        fos.close()

        return imageFile.absolutePath
    }


}