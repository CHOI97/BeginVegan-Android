package com.example.presentation.view.gallery.viewModel

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.presentation.view.gallery.model.GalleryImage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(): ViewModel(){

    private val _permissionState = MutableLiveData(false)
    val permissionState: LiveData<Boolean> get() = _permissionState

    private val _imageList = MutableLiveData<MutableList<GalleryImage>>(mutableListOf())
    val imageList: LiveData<MutableList<GalleryImage>> get() = _imageList

    private val _selectImage = MutableLiveData<GalleryImage?>(null)
    val selectImage: LiveData<GalleryImage?> get() = _selectImage

    private val _resultImage = MutableLiveData<GalleryImage>()
    val resultImage: LiveData<GalleryImage> get() = _resultImage

    fun updateResultImage(resultImage: GalleryImage){
        _resultImage.value = resultImage
    }
    fun updateSelectImage(imageData: GalleryImage){
        _selectImage.value = imageData
    }
    fun updatePermissionState(isPermission: Boolean){
        _permissionState.value = isPermission
    }
    fun fetchGalleryImage(imageList: MutableList<GalleryImage>){
        _imageList.value = imageList
        Timber.d("fetch ImageList $_imageList ")
    }
}