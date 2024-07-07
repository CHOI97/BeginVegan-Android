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
class GalleryViewModel  @Inject constructor(): ViewModel(){

    private val _permissionState = MutableLiveData<Boolean>(false)
    val permissionState: LiveData<Boolean> get() = _permissionState

    private val _imageList = MutableLiveData<MutableList<GalleryImage>>(mutableListOf())
    val imageList: LiveData<MutableList<GalleryImage>> get() = _imageList

    fun updatePermissionState(isPermission: Boolean){
        _permissionState.value = isPermission
    }
    fun fetchGalleryImage(imageList: MutableList<GalleryImage>){
        _imageList.value = imageList
        Timber.d("fetch ImageList $_imageList ")
    }
    private fun getCursor(context: Context): Cursor? {
        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.DATE_TAKEN
        )
        return context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"
        )
    }

    fun showGallery(context: Context) {
        viewModelScope.launch {
            Timber.d("비동기 시작: ShowGallery")
            try {
                val cursor = getCursor(context)
                cursor?.use {
                    if (it.count == 0) {
                        Timber.d("No images found")
                    } else {
                        val images = mutableListOf<GalleryImage>()
                        val idColNum = it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
                        val titleColNum = it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE)
                        val dateTakenColNum = it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN)

                        while (it.moveToNext()) {
                            val id = it.getLong(idColNum)
                            val title = it.getString(titleColNum)
                            val dateTaken = it.getLong(dateTakenColNum)
                            val imageUri = ContentUris.withAppendedId(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                id
                            )
                            images.add(GalleryImage(imageUri, false, dateTaken))
                            Timber.d("id: $id, title: $title, dateTaken: $dateTaken, imageUri: $imageUri")
                        }
                        _imageList.value = images
                    }
                } ?: run {
                    Timber.d("Cursor is null")
                }
            } catch (e: Exception) {
                Timber.e(e, "에러: 스토리지 접근 권한을 허가해주세요")
            }
        }
    }
//    fun loadGalleryImages() {
//        viewModelScope.launch {
//            try {
//                val cursor = getCursor()
//                val imageUris = mutableListOf<Uri>()
//                cursor?.use {
//                    while (it.moveToNext()) {
//                        val id = it.getLong(it.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID))
//                        val imageUri = ContentUris.withAppendedId(
//                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                            id
//                        )
//                        imageUris.add(imageUri)
//                    }
//                }
//                _imageList.value = imageUris
//            } catch (e: Exception) {
//                // Handle the error
//            }
//        }
//    }
//
//    private fun getCursor(context: Context): Cursor? {
//        val projection = arrayOf(
//            MediaStore.Images.ImageColumns._ID,
//            MediaStore.Images.ImageColumns.TITLE,
//            MediaStore.Images.ImageColumns.DATE_TAKEN
//        )
//        val sortOrder = "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"
//
//        return context.contentResolver.query(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//            projection,
//            null,
//            null,
//            sortOrder
//        )
//    }
//
//    companion object {
//        const val REQUEST_CODE_PERMISSIONS = 2000
//    }
}