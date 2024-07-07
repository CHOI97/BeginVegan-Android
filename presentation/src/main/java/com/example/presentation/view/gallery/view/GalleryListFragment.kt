package com.example.presentation.view.gallery.view

import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentGalleryListBinding
import com.example.presentation.view.gallery.adpater.GalleryRVAdapter
import com.example.presentation.view.gallery.model.GalleryImage
import com.example.presentation.view.gallery.viewModel.GalleryViewModel
import com.takusemba.cropme.CropLayout
import com.takusemba.cropme.OnCropListener
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.ByteArrayOutputStream

class GalleryListFragment : BaseFragment<FragmentGalleryListBinding>(R.layout.fragment_gallery_list) {
    private val imageList = arrayListOf<Uri>()
    private lateinit var cropLayout: CropLayout

    private val viewModel: GalleryViewModel by activityViewModels()

    private lateinit var galleryRVAdapter: GalleryRVAdapter
    override fun init() {
//        setupCropLayout()
        viewModel.permissionState.observe(this){
            // 갤러리 이미지 가져와서 뿌리기
            showGallery()
            setGalleryAdapter()
        }
        viewModel.imageList.observe(this){
            Timber.d("Observe ImageList $imageList ")
        }

    }
    private fun setGalleryAdapter(){
        galleryRVAdapter = GalleryRVAdapter()
        binding.rvGallery.adapter = galleryRVAdapter
        galleryRVAdapter.submitList(viewModel.imageList.value)
    }


    private fun getImageUri(context: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            inImage,
            "Title",
            null
        )
        return Uri.parse(path)
    }

    fun absolutelyPath(path: Uri): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? = requireContext().contentResolver.query(path, proj, null, null, null)
        val index = c!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c.moveToFirst()
        return c.getString(index)
    }
    private fun getCursor(): Cursor? {
        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.TITLE,
            MediaStore.Images.ImageColumns.DATE_TAKEN
        )
        return requireContext().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"
        )
    }

    private fun showGallery() {
        lifecycleScope.launch {
            Timber.d("비동기 시작: ShowGallery")
            try {
                val cursor = getCursor()
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
                        Timber.d("fetch ImageList $imageList ")
                        viewModel.fetchGalleryImage(images)
                    }
                } ?: run {
                    Timber.d("Cursor is null")
                }
            } catch (e: Exception) {
                Timber.e(e, "에러: 스토리지 접근 권한을 허가해주세요")
            }
        }
    }

        companion object {
        // REQUEST CODE
        const val IMAGE_URI = "imageUri"
        const val IMAGE_PATH = "imagePath"
        const val REQUEST_CODE_FOR_INTENT = 1002
        const val REQUEST_CODE_PERMISSIONS = 2000
    }


//
//    private fun setupCropLayout() {
////        cropLayout = binding.cropLayout
//        cropLayout.addOnCropListener(object : OnCropListener {
//            override fun onSuccess(bitmap: Bitmap) {
//                val image = getImageUri(requireContext(), bitmap)
//                val path = absolutelyPath(image!!)
//                // 이미지 저장 메소드 호출
//                onSaveButtonClick(image, path)
//            }
//
//            override fun onFailure(e: Exception) {
//                Log.e("Failure", "$e")
//                Log.d("Failure", "failed")
//            }
//        })
//    }
//
//    private fun requestPermission() {
//        var permissionCheck = ContextCompat.checkSelfPermission(
//            requireContext(),
//            REQUIRED_PERMISSIONS[0]
//        )
//        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//            // 설명 필요 여부 확인
//            if (ActivityCompat.shouldShowRequestPermissionRationale(
//                    requireActivity(),
//                    REQUIRED_PERMISSIONS[0]
//                )
//            ) {
//                Log.d("설명", "사용자가 요청을 거부 한적이 있다.")
//                ActivityCompat.requestPermissions(
//                    requireActivity(),
//                    REQUIRED_PERMISSIONS,
//                    REQUEST_CODE_PERMISSIONS
//                )
//            } else {
//                Log.d("설명x", "사용자가 요청을 거부 한적이 없다.")
//                ActivityCompat.requestPermissions(
//                    requireActivity(),
//                    REQUIRED_PERMISSIONS,
//                    REQUEST_CODE_PERMISSIONS
//                )
//            }
//        } else {
//            showGallery()
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            REQUEST_CODE_PERMISSIONS -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    showGallery()
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "권한이 거부되었습니다. 갤러리에 접근하려면 권한을 허용해야 합니다.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                return
//            }
//        }
//    }
//
//    private fun getCursor(): Cursor? {
//        val projection = arrayOf(
//            MediaStore.Images.ImageColumns._ID,
//            MediaStore.Images.ImageColumns.TITLE,
//            MediaStore.Images.ImageColumns.DATE_TAKEN
//        )
//        val sortOrder = "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"
//
//        return requireContext().contentResolver.query(
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//            projection,
//            null,
//            null,
//            sortOrder
//        )
//    }
//
//    private fun showGallery() {
//        lifecycleScope.launch {
//            Log.d("비동기 시작", "ShowGallery")
//            try {
//                val cursor = getCursor()
//                when (cursor?.count) {
//                    null -> {
//                        Log.d("NULL", "NULL")
//                    }
//                    0 -> {
//                        Log.d("EMPTY", "No images found")
//                    }
//                    else -> {
//                        while (cursor.moveToNext()) {
//                            val idColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID)
//                            val titleColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE)
//                            val dateTakenColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN)
//
//                            val id = cursor.getLong(idColNum)
//                            val title = cursor.getString(titleColNum)
//                            val dateTaken = cursor.getLong(dateTakenColNum)
//                            val imageUri = ContentUris.withAppendedId(
//                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                id
//                            )
//
//                            imageList.add(imageUri)
//
//                            Log.d(
//                                "LOGGING",
//                                "id: $id, title:$title, dateTaken : $dateTaken, imageUri : $imageUri"
//                            )
//                        }
//                        cursor.close()
//                    }
//                }
//            } catch (e: Exception) {
//                Toast.makeText(requireContext(), "에러: 스토리지 접근 권한을 허가해주세요", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun getImageUri(context: Context, inImage: Bitmap): Uri? {
//        val bytes = ByteArrayOutputStream()
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//        val path = MediaStore.Images.Media.insertImage(
//            context.contentResolver,
//            inImage,
//            "Title",
//            null
//        )
//        return Uri.parse(path)
//    }
//
//    fun absolutelyPath(path: Uri): String {
//        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
//        val c: Cursor? = requireContext().contentResolver.query(path, proj, null, null, null)
//        val index = c!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        c.moveToFirst()
//        return c.getString(index)
//    }
//
//    // 저장하기 버튼 클릭 리스너 메소드
//    private fun onSaveButtonClick(imageUri: Uri, imagePath: String) {
//        // 저장 로직 구현
//        Log.d("Save", "Image saved at URI: $imageUri, Path: $imagePath")
//    }
//
//    // 삭제하기 버튼 클릭 리스너 메소드
//    private fun onDeleteButtonClick(imageUri: Uri) {
//        // 삭제 로직 구현
//        Log.d("Delete", "Image deleted at URI: $imageUri")
//    }
//
//    companion object {
//        // REQUEST CODE
//        const val IMAGE_URI = "imageUri"
//        const val IMAGE_PATH = "imagePath"
//        const val REQUEST_CODE_FOR_INTENT = 1002
//        const val REQUEST_CODE_PERMISSIONS = 2000
//    }
}
