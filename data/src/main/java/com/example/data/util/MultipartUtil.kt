package com.example.data.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object MultipartUtil {
    fun createPartFromString(value: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
    }

    fun createPartFromBoolean(value: Boolean): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), value.toString())
    }

    fun prepareFilePart(partName: String, file: File?): MultipartBody.Part? {
        return file?.let {
            val requestFile = it.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData(partName, it.name, requestFile)
        }
    }
}