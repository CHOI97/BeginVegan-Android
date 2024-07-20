package com.example.presentation.view.tips.view

import android.graphics.Typeface
import android.view.Gravity
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.domain.model.tips.MagazineContent
import com.example.domain.model.tips.TipsMagazineDetail
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.FragmentTipsMagazineDetailBinding
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.tips.viewModel.MagazineViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class TipsMagazineDetailFragment : BaseFragment<FragmentTipsMagazineDetailBinding>(R.layout.fragment_tips_magazine_detail) {
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    @Inject
    lateinit var bookmarkController: BookmarkController
    private val magazineViewModel:MagazineViewModel by activityViewModels()

    override fun init() {
        binding.lifecycleOwner = this

        magazineViewModel.magazineDetail.observe(this){
            if(it != null) setView(it)
        }
        goBackUp()
    }

    private fun goBackUp(){
        binding.includedToolbar.ibBackUp.setOnClickListener {
//            mainNavigationHandler.popBackStack()
            findNavController().popBackStack()
        }
    }

    private fun transferDate(date:String):String{
        val stringToDate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        val newDate = LocalDateTime.parse(date, stringToDate)

        val dateToString = DateTimeFormatter.ofPattern("yyyy. MM. dd")
        return newDate.format(dateToString)
    }

    private fun setView(it: TipsMagazineDetail){
        binding.tvMagazineTitle.text = it.title
        binding.tvWriter.text = it.editor
        binding.tvDate.text = transferDate(it.createdDate)

        Glide.with(this)
            .load(it.thumbnail)
            .into(binding.ivThumbnail)

        for(content in it.magazineContents){
            createContentTextView(content)
        }

        binding.tbInterest.setOnCheckedChangeListener(null)
        binding.tbInterest.isChecked = it.isBookmarked
        binding.tbInterest.setOnCheckedChangeListener { _, isChecked ->
            lifecycleScope.launch {
                if(isChecked){
                    bookmarkController.postBookmark(it.id, "MAGAZINE")
                    Snackbar.make(binding.clLayout, getString(R.string.toast_scrap_done), Snackbar.LENGTH_SHORT)
                        .setAction(getString(R.string.toast_scrap_action)){
                            mainNavigationHandler.navigateTipsMagazineDetailToMyMagazine()
                        }
                        .setActionTextColor(resources.getColor(R.color.color_primary_variant_02))
                        .show()
                }else{
                    bookmarkController.deleteBookmark(it.id, "MAGAZINE")
                    Snackbar.make(binding.clLayout, getString(R.string.toast_scrap_undo), Snackbar.LENGTH_SHORT)
                        .setAction(getString(R.string.toast_scrap_action)){
                            mainNavigationHandler.navigateTipsMagazineDetailToMyMagazine()
                        }
                        .setActionTextColor(resources.getColor(R.color.color_primary_variant_02))
                        .show()
                }
                it.isBookmarked = isChecked
                magazineViewModel.setMagazineDetail(it)
            }
        }
    }

    private fun createContentTextView(content: MagazineContent){
        val textView = TextView(context).apply {
            text = content.content
            textSize = 14f
            setTextColor(ContextCompat.getColor(context, R.color.color_text_01))
            gravity = Gravity.CENTER
            if(content.isBold) setTypeface(null, Typeface.BOLD)
        }

        val parentLayout = binding.llMagazineContents
        parentLayout.addView(textView)
    }
}