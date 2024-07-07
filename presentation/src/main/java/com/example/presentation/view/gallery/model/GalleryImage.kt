package com.example.presentation.view.gallery.model

import android.graphics.Bitmap
import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GalleryImage(
    val imageUri: Uri?,
    val check: Boolean = false,
    val imagePath: String? = null
) : Parcelable
