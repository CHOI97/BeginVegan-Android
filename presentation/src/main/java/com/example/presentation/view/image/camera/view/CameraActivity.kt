//package com.example.presentation.view.image.camera.view
//
//import android.Manifest
//import android.app.Activity
//import android.app.AlertDialog
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.provider.MediaStore
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.activity.viewModels
//import androidx.core.app.ActivityCompat
//import androidx.core.content.FileProvider
//import com.example.presentation.BuildConfig
//import com.example.presentation.R
//import com.example.presentation.base.BaseActivity
//import com.example.presentation.databinding.ActivityCameraBinding
//import com.example.presentation.view.image.camera.viewModel.CameraViewModel
//import dagger.hilt.android.AndroidEntryPoint
//import java.io.File
//import java.io.IOException
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//
//@AndroidEntryPoint
//class CameraActivity : BaseActivity<ActivityCameraBinding>(R.layout.activity_camera) {
//
//    private val viewModel: CameraViewModel by viewModels()
//
//    private val cameraPermission = Manifest.permission.CAMERA
////    private val storagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE
//
//    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
//
//    private var photoUri: Uri? = null
//
//    override fun initViewModel() {
//    }
//
//    private val requestPermissionLauncher: ActivityResultLauncher<String> =
//        registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted ->
//            if (isGranted) {
//                openCamera()
//            } else {
//                showPermissionRationaleDialog(this)
//            }
//        }
//
//    override fun init() {
//        cameraLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val imageUri = result.data?.data
//                logMessage("Success Image Capture")
//                logMessage("imageUri = $imageUri")
//            } else {
//                logMessage("Failed or Cancel Image Capture")
//                finish()
//            }
//        }
//
//        checkPermissionCamera()
////        requestStoragePermission.launch(storagePermission)
//
////        viewModel.isCameraPermissionGranted.observe(this) { granted ->
////            if (granted == true) {
////                openCamera()
////            } else {
////                showToast("카메라 권한이 필요합니다.")
////            }
////        }
//
////        viewModel.isStoragePermissionGranted.observe(this) { granted ->
////            if (granted == true) {
////                // 저장 권한이 허가된 경우에 추가적인 작업을 수행할 수 있습니다.
////            } else {
////                showToast("저장 권한이 필요합니다.")
////            }
////        }
//    }
//
//    private fun checkPermissionCamera() {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                PERMISSION_CAMERA
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            openCamera()
//        } else {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_CAMERA)) {
//                showPermissionDeniedDialog(this)
//            } else {
//                requestPermissionLauncher.launch(cameraPermission)
//            }
//
//        }
//    }
//
////    private val requestCameraPermission = registerForActivityResult(
////        ActivityResultContracts.RequestPermission()
////    ) {
////        viewModel.updateCameraPermissionState(it)
////    }
//
////    private val requestStoragePermission = registerForActivityResult(
////        ActivityResultContracts.RequestPermission()
////    ) {
////        viewModel.updateStoragePermissionState(it)
////    }
//
//    private fun openCamera() {
//        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (cameraIntent.resolveActivity(packageManager) != null) {
//            val photoFilePath: String? = try {
//                createCacheImageFile()
//            } catch (ex: IOException) {
//                null
//            }
//            photoFilePath?.also {
//                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
//                cameraLauncher.launch(cameraIntent)
//            }
//        } else {
//            showToast("No camera app found")
//        }
//    }
//
//    private fun createCacheImageFile(): String {
//        // 현재 날짜와 시간을 형식화한 문자열 생성
//        val timeStamp: String =
//            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
//
//        // 앱의 캐시 디렉토리 경로를 가져옴
//        val cacheDir: File = cacheDir
//
//        // 캐시 디렉토리에 임시 파일 생성
//        val tempFile = File.createTempFile(
//            "JPEG_${timeStamp}_", // 파일 이름의 접두사
//            ".jpg",               // 파일 확장자
//            cacheDir              // 파일을 생성할 디렉토리
//        )
//
//        // 생성된 파일의 절대 경로 반환
//        return tempFile.absolutePath
//    }
//
//    // 권한 재요청
//    private fun showPermissionRationaleDialog(context: Context) {
//        var isRetry = false
//        val dialog = AlertDialog.Builder(context)
//            .setTitle("권한 재요청 안내")
//            .setMessage(
//                "해당 권한을 거부할 경우, 다음 기능의 사용이 불가능해요." +
//                        "\n· Map 리뷰 작성 시, 이미지 등록 " +
//                        "\n· Mypage 프로필 이미지 등록"
//            )
//            .setPositiveButton("권한재요청") { _, _ ->
//                isRetry = true
//            }
//            .setNegativeButton("닫기") { dialog, _ ->
//                dialog.dismiss()
//            }
//            .show()
//        dialog.setOnDismissListener {
//            if (!isRetry) {
//                showPermissionDeniedDialog(context)
//            }
//        }
//    }
//
//    // 권한 허용 안함
//    private fun showPermissionDeniedDialog(context: Context) {
//        val dialog = AlertDialog.Builder(context)
//            .setTitle("기능 사용 불가 안내")
//            .setMessage(
//                "카메라 사용에 대한 권한 사용을 거부하셨어요. \n" +
//                        "\n" +
//                        "기능 사용을 원하실 경우 ‘휴대폰 설정 > 애플리케이션 관리자’에서 해당 앱의 권한을 허용해 주세요."
//            )
//            .setNegativeButton("확인") { dialog, _ ->
//                dialog.dismiss()
//            }
//            .show()
//        dialog.setOnDismissListener {
//            this@CameraActivity.finish()
//        }
//    }
//
//    companion object {
//        private const val REQUEST_IMAGE_CAPTURE = 1
//        private const val PERMISSION_CAMERA = Manifest.permission.CAMERA
////        private const val WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
//    }
//}
package com.example.presentation.view.image.camera.view

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.example.presentation.BuildConfig
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityCameraBinding
import com.example.presentation.view.image.camera.viewModel.CameraViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class CameraActivity : BaseActivity<ActivityCameraBinding>(R.layout.activity_camera) {

    private val viewModel: CameraViewModel by viewModels()

    private val cameraPermission = Manifest.permission.CAMERA

    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private var photoFilePath: String? = null

    override fun initViewModel() {
    }

    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                openCamera()
            } else {
                showPermissionRationaleDialog(this)
            }
        }

    override fun init() {
        cameraLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                logMessage("Success Image Capture")
                logMessage("photoFilePath = $photoFilePath")
            } else {
                logMessage("Failed or Cancel Image Capture")
                finish()
            }
        }

        checkPermissionCamera()
    }

    private fun checkPermissionCamera() {
        if (ActivityCompat.checkSelfPermission(
                this,
                PERMISSION_CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera()
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_CAMERA)) {
                showPermissionDeniedDialog(this)
            } else {
                requestPermissionLauncher.launch(cameraPermission)
            }
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            photoFilePath = try {
                createCacheImageFile()
            } catch (ex: IOException) {
                null
            }
            photoFilePath?.also {
                val photoFile = File(it)
                val photoUri = FileProvider.getUriForFile(
                    this,
                    "${this.packageName}.fileprovider",
                    photoFile
                )
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                cameraLauncher.launch(cameraIntent)
            }
        } else {
            showToast("No camera app found")
        }
    }

    private fun createCacheImageFile(): String {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val cacheDir: File = cacheDir
        val tempFile = File.createTempFile(
            "JPEG_${timeStamp}_", // 파일 이름의 접두사
            ".jpg",               // 파일 확장자
            cacheDir              // 파일을 생성할 디렉토리
        )
        return tempFile.absolutePath
    }

    // 권한 재요청
    private fun showPermissionRationaleDialog(context: Context) {
        var isRetry = false
        val dialog = AlertDialog.Builder(context)
            .setTitle("권한 재요청 안내")
            .setMessage(
                "해당 권한을 거부할 경우, 다음 기능의 사용이 불가능해요." +
                        "\n· Map 리뷰 작성 시, 이미지 등록 " +
                        "\n· Mypage 프로필 이미지 등록"
            )
            .setPositiveButton("권한재요청") { _, _ ->
                isRetry = true
                requestPermissionLauncher.launch(cameraPermission)
            }
            .setNegativeButton("닫기") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
        dialog.setOnDismissListener {
            if (!isRetry) {
                showPermissionDeniedDialog(context)
            }
        }
    }

    // 권한 허용 안함
    private fun showPermissionDeniedDialog(context: Context) {
        val dialog = AlertDialog.Builder(context)
            .setTitle("기능 사용 불가 안내")
            .setMessage(
                "카메라 사용에 대한 권한 사용을 거부하셨어요. \n" +
                        "\n" +
                        "기능 사용을 원하실 경우 ‘휴대폰 설정 > 애플리케이션 관리자’에서 해당 앱의 권한을 허용해 주세요."
            )
            .setNegativeButton("확인") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
        dialog.setOnDismissListener {
            this@CameraActivity.finish()
        }
    }

    companion object {
        private const val PERMISSION_CAMERA = Manifest.permission.CAMERA
    }
}
