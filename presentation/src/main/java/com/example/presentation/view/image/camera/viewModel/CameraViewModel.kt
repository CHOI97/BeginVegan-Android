package com.example.presentation.view.image.camera.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {
    private val _isCameraPermissionGranted = MutableLiveData<Boolean>()
    val isCameraPermissionGranted: LiveData<Boolean> get() = _isCameraPermissionGranted

    private val _isStoragePermissionGranted = MutableLiveData<Boolean>()
    val isStoragePermissionGranted: LiveData<Boolean> get() = _isStoragePermissionGranted

    fun updateCameraPermissionState(granted: Boolean) {
        _isCameraPermissionGranted.value = granted
    }

    fun updateStoragePermissionState(granted: Boolean) {
        _isStoragePermissionGranted.value = granted
    }
}