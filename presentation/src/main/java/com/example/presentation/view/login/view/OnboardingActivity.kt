package com.example.presentation.view.login.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract.Contacts.Photo
import android.util.Base64
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.adapter.onboarding.OnboardingAdapter
import com.example.presentation.base.BaseActivity
import com.example.presentation.databinding.ActivityOnboardingBinding
import com.example.presentation.view.gallery.model.GalleryImage
import com.example.presentation.view.gallery.view.GalleryActivity
import com.example.presentation.view.gallery.view.PhotoSelectDialog
import com.example.presentation.view.login.viewModel.OnboardingViewModel
import com.example.presentation.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>(R.layout.activity_onboarding) {
    private lateinit var photoSelectDialog: PhotoSelectDialog

    private lateinit var emptyErrorText: String
    private lateinit var invalidErrorText: String

    private val viewModel: OnboardingViewModel by viewModels()

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun initViewModel() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    override fun init() {
        setImageResultLauncher()
        setErrorText()
        setInputHelper()
        setOnClickProfile()
        setDropdown()
        setObserve()
        updateUserInfo()

        viewModel.userInfoState.observe(this) { isUserInfoState ->
            if (isUserInfoState) {
                navigateToMain()
            }else{
                showToast("유저 추가 정보 입력 오류")
            }
        }
    }

    private fun updateUserInfo() {
        binding.btnOnboardingNext.setOnClickListener {
            showToast("nick: ${binding.etOnboardingEditNick.text}\nveganType: ${binding.actvOnboardingEditDropdown.text}")
            val nickName = binding.etOnboardingEditNick.text.toString()
            val veganLevel = binding.actvOnboardingEditDropdown.text.toString()
            viewModel.saveUserInfo(nickName,veganLevel)
        }
    }

    private fun setObserve() {
        viewModel.validNickName.observe(this) {
            binding.btnOnboardingNext.isEnabled = viewModel.checkValid()
            logMessage("${viewModel.nickName}")
        }
        viewModel.validVeganLevel.observe(this) {
            binding.btnOnboardingNext.isEnabled = viewModel.checkValid()
            logMessage("${viewModel.veganLevel}")
        }
    }

    private fun setErrorText() {
        emptyErrorText = getString(R.string.helper_text_nickname_default)
        invalidErrorText = getString(R.string.helper_text_nickname_check)
    }

    private fun setDropdown() {
        val adapter = OnboardingAdapter(this, R.layout.item_dropdown, items)

        binding.actvOnboardingEditDropdown.setAdapter(adapter)

        binding.actvOnboardingEditDropdown.setOnItemClickListener { parent, view, position, id ->
            adapter.setSelectedItemPosition(position)
            viewModel.setValidVeganLevel()
        }
    }

    private fun setImageResultLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    var imageData: GalleryImage? =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            result.data?.getParcelableExtra("IMAGE_DATA", GalleryImage::class.java)
                        } else {
                            intent.getParcelableExtra<GalleryImage>("IMAGE_DATA")
                        }
                    Glide.with(this).load(imageData?.imageUri).into(binding.civOnboardingProfile)
                    viewModel.updateProfileImageUri(
                        GalleryImage(
                            imageData?.imageUri,
                            false,
                            imageData?.imagePath
                        )
                    )
                }
            }
    }

    private fun setInputHelper() {
        binding.tilOnboardingEditNick.error = emptyErrorText
        viewModel.setValidNickName(false)

        viewModel.nickName.observe(this) { nickName ->
            binding.tilOnboardingEditNick.error = if (nickName.isNullOrEmpty()) {
                // 비어 있는 경우
                viewModel.setValidNickName(false)
                emptyErrorText
            } else {
                if (viewModel.validateNickName(nickName)) {
                    // 유효한 경우
                    viewModel.setValidNickName(true)
                    ""
                } else {
                    // 유효하지 않은 경우
                    viewModel.setValidNickName(false)
                    invalidErrorText
                }
            }
        }
    }

    private fun setOnClickProfile() {

        binding.civOnboardingProfile.onThrottleClick {
            photoSelectDialog = if (viewModel.profileImageUri.value == null) {
                PhotoSelectDialog(false)
            } else {
                PhotoSelectDialog(true)
            }
            photoSelectDialog.show(supportFragmentManager, "PhotoSelectDialog")
            photoSelectDialog.setDialogClickListener(object :
                PhotoSelectDialog.DialogPhotoSelectClickListener {
                override fun onClickCamera() {
                }

                override fun onClickGallery() {
                    photoSelectDialog.dismiss()
                    val intent = Intent(this@OnboardingActivity, GalleryActivity::class.java)
                    activityResultLauncher.launch(intent)
                }

                override fun onClickDefault() {
                    photoSelectDialog.dismiss()
                    viewModel.updateProfileImageUri(null)
                    Glide.with(this@OnboardingActivity)
                        .load(R.drawable.illus_user_profile_default)
                        .into(binding.civOnboardingProfile)
                }
            })

        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        val items = listOf(
            "알고 있지 않아요",
            "비건",
            "락토 베지테리언",
            "오보 베지테리언",
            "락토 오보 베지테리언",
            "페스코 베지테리언",
            "폴로 베지테리언",
            "플렉시테리언"
        )
    }

}