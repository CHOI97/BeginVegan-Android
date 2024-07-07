package com.example.presentation.view.gallery.view

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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.presentation.config.navigation.home.HomeNavigationImpl
import com.example.presentation.databinding.ActivityGalleryBinding
import com.takusemba.cropme.CropLayout
import com.takusemba.cropme.OnCropListener
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class GalleryActivity: BaseActivity<ActivityGalleryBinding>(R.layout.activity_gallery) {
    override fun initViewModel() {
    }

    override fun init() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcw_gallery) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.ibNextUpload.setOnClickListener {

        }
        binding.ibBackUp.setOnClickListener {

        }
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