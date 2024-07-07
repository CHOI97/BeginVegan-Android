package com.example.presentation.view.gallery.view

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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.config.navigation.home.HomeNavigationImpl
import com.example.presentation.databinding.ActivityGalleryBinding
import com.example.presentation.view.gallery.viewModel.GalleryViewModel
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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcw_gallery) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.ibNextUpload.setOnClickListener {

        }
        binding.ibBackUp.setOnClickListener {

        }

        when {
            // 갤러리 접근 권한이 있는 경우
            ContextCompat.checkSelfPermission(
                this,
                galleryRequestPermission
            ) == PackageManager.PERMISSION_GRANTED -> {
//                showGallery()
                Log.d("LOG", "갤러리 접근 권한이 있는 경우")
            }
            // 갤러리 접근 권한이 없는 경우 && 교육용 팝업
            shouldShowRequestPermissionRationale( galleryRequestPermission) -> {
//                requestPermission()
                Log.d("LOG", "갤러리 접근 권한이 없는 경우 && 교육용 팝업")
            }
            // 권한 요청 하기
            else -> {
//                requestPermission()
            }
        }
    }

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        when (it) {
            true -> {
                Toast.makeText(this, "권한 허가", Toast.LENGTH_SHORT).show()
                viewModel.updatePermissionState(true)
            }

            false -> {
                Toast.makeText(this, "권한 거부", Toast.LENGTH_SHORT).show()
                viewModel.updatePermissionState(false)
            }
        }
    }

        private fun getCursor(): Cursor? {
        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.DATE_TAKEN
        )
        val sortOrder = "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"

        return this.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            sortOrder
        )
    }


//    private fun updateToolbar(fragment: Fragment) {
//        when (fragment) {
//            is HomeFragment -> {
//                binding.toolbar.title = "Home"
//                binding.toolbar.setOnClickListener {
//                    // HomeFragment의 클릭 리스너
//                }
//            }
//            is ProfileFragment -> {
//                binding.toolbar.title = "Profile"
//                binding.toolbar.setOnClickListener {
//                    // ProfileFragment의 클릭 리스너
//                }
//            }
//            // 다른 Fragment에 대한 설정
//        }
//    }

}