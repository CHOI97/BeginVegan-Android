package com.example.presentation.view.Image.gallery.view

import android.Manifest
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.config.navigation.home.HomeNavigationImpl
import com.example.presentation.databinding.ActivityGalleryBinding
import com.example.presentation.view.Image.gallery.viewModel.GalleryViewModel
import com.takusemba.cropme.CropLayout
import com.takusemba.cropme.OnCropListener
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class GalleryActivity : BaseActivity<ActivityGalleryBinding>(R.layout.activity_gallery) {

    private val viewModel: GalleryViewModel by viewModels()

    private val galleryRequestPermission = if (Build.VERSION.SDK_INT < 33) {
        Manifest.permission.READ_EXTERNAL_STORAGE
    } else {
        Manifest.permission.READ_MEDIA_IMAGES
    }


    override fun initViewModel() {
    }

    override fun init() {
        requestPermission.launch(galleryRequestPermission)

        viewModel.resultImage.observe(this) {
            if (it != null) {
                intent.putExtra("IMAGE_DATA", viewModel.resultImage.value)
                logMessage("Activity Result Launcher ${viewModel.resultImage.value}")
                setResult(RESULT_OK, intent)
                finish()
            }
        }

    }

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        when (it) {
            true -> {
                logMessage("권한 허가")
                viewModel.updatePermissionState(true)
            }

            false -> {
                logMessage("권한 거부")
                viewModel.updatePermissionState(false)
            }
        }
    }

}