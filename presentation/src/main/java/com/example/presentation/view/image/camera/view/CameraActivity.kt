package com.example.presentation.view.image.camera.view

import android.Manifest
import android.content.Intent
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityCameraBinding
import com.example.presentation.view.image.camera.viewModel.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraActivity : BaseActivity<ActivityCameraBinding>(R.layout.activity_camera) {

    private val viewModel: CameraViewModel by viewModels()

    private val cameraPermission = Manifest.permission.CAMERA
    private val storagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

    override fun initViewModel() {
    }

    override fun init() {
        requestCameraPermission.launch(cameraPermission)
        requestStoragePermission.launch(storagePermission)

        viewModel.isCameraPermissionGranted.observe(this) { granted ->
            if (granted == true) {
                openCamera()
            } else {
                showToast("카메라 권한이 필요합니다.")
            }
        }

        viewModel.isStoragePermissionGranted.observe(this) { granted ->
            if (granted == true) {
                // 저장 권한이 허가된 경우에 추가적인 작업을 수행할 수 있습니다.
            } else {
                showToast("저장 권한이 필요합니다.")
            }
        }
    }

    private val requestCameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        viewModel.updateCameraPermissionState(it)
    }

    private val requestStoragePermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        viewModel.updateStoragePermissionState(it)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }
}
