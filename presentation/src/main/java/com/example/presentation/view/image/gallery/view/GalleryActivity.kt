package com.example.presentation.view.image.gallery.view

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.example.presentation.databinding.ActivityGalleryBinding
import com.example.presentation.view.image.gallery.viewModel.GalleryViewModel

class GalleryActivity : BaseActivity<ActivityGalleryBinding>(R.layout.activity_gallery) {

    private val viewModel: GalleryViewModel by viewModels()

    override fun initViewModel() {
        viewModel.resultImage.observe(this) {
            if (it != null) {
                intent.putExtra("IMAGE_DATA", viewModel.resultImage.value)
                logMessage("Activity Result Launcher ${viewModel.resultImage.value}")
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }

    // 여러 권한을 배열로 요청
    private val galleryPermissions: Array<String> = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
            )
        }

        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
            arrayOf(
                Manifest.permission.READ_MEDIA_IMAGES,
            )
        }

        else -> {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private lateinit var requestPermissionsLauncher: ActivityResultLauncher<Array<String>>

    override fun init() {
        requestPermissionsLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions.all { it.value }) {
                logMessage("모든 권한이 승인되었습니다.")
            } else {
                logMessage("일부 권한이 거부되었습니다.")
                handlePermissionDenial()
            }
        }
        requestPermissionsLauncher.launch(galleryPermissions)
    }

    private fun handlePermissionDenial() {
        if (galleryPermissions.any {
                ActivityCompat.shouldShowRequestPermissionRationale(this, it)
            }
        ) {
            showPermissionRationaleDialog(this)
        } else {
            showPermissionDeniedDialog(this)
        }
    }

    private fun showPermissionRationaleDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("권한 재요청 안내")
            .setMessage(
                "해당 권한을 거부할 경우, 다음 기능의 사용이 불가능해요." +
                        "\n· Map 리뷰 작성 시, 이미지 등록 " +
                        "\n· Mypage 프로필 이미지 등록"
            )
            .setPositiveButton("권한재요청") { _, _ ->
                requestPermissionsLauncher.launch(galleryPermissions)
            }
            .setNegativeButton("닫기") { _, _ ->
                showPermissionDeniedDialog(context)
            }
            .show()
    }

    private fun showPermissionDeniedDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("기능 사용 불가 안내")
            .setMessage("저장소에 대한 권한 사용을 거부하셨어요.\n\n기능사용을 원하실 경우 '휴대폰 설정 > 애플리케이션 관리자'에서 해당 앱의 권한을 허용해 주세요.")
            .setNegativeButton("확인") { _, _ ->
                this@GalleryActivity.finish()
            }
            .show()
    }
}
