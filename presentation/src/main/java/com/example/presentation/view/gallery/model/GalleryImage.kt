package com.example.presentation.view.gallery.model

import android.net.Uri

data class GalleryImage(
    val imageUri: Uri,
    val check: Boolean = false,
)
