package com.example.presentation.view.gallery.view

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
import com.example.presentation.view.gallery.model.GalleryImage
import com.example.presentation.view.gallery.viewModel.GalleryViewModel
import com.takusemba.cropme.OnCropListener
import timber.log.Timber
import java.io.ByteArrayOutputStream

class SelectImageFragment : BaseFragment<FragmentGallerySelectImageBinding>(R.layout.fragment_gallery_select_image){
//    private val args: SelectImageFragmentArgs by navArgs()

    private val viewModel: GalleryViewModel by activityViewModels()
    override fun init() {
        viewModel.selectImage.value?.let { selectedImage ->
            selectedImage.imageUri?.let { binding.clCropper.setUri(it) }
        }
        binding.ibDone.setOnClickListener{
            binding.clCropper.crop()
        }
        binding.clCropper.addOnCropListener(object: OnCropListener{
            override fun onFailure(e: Exception) {
                Timber.d("사진 크롭 실패")
            }

            override fun onSuccess(bitmap: Bitmap) {
                val image = getImageUri(requireContext(),bitmap)
                val path = absolutelyPath(image)
                Timber.d("사진 크롭 성공\n"+
                        "$bitmap\n"+
                        "$image\n"+
                        "$path\n")

                viewModel.updateResultImage(GalleryImage(image,false,path))
            }

        })

    }

    private fun getImageUri(context: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }

    fun absolutelyPath(path: Uri?): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? =
            path?.let { requireContext().contentResolver.query(it, proj, null, null, null) }
        val index = c!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c.moveToFirst()
        return c.getString(index)
    }

}