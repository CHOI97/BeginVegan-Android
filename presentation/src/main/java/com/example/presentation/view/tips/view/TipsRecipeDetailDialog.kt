package com.example.presentation.view.tips.view

import android.graphics.Typeface
import android.view.Gravity
import android.view.KeyEvent
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import com.example.domain.model.RecipeBlock
import com.example.domain.model.RecipeIngredient
import com.example.domain.model.TipsRecipeDetail
import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.config.navigation.MainNavigationHandler
import com.example.presentation.databinding.DialogRecipeDetailBinding
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.tips.viewModel.RecipeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class TipsRecipeDetailDialog:BaseDialogFragment<DialogRecipeDetailBinding>(R.layout.dialog_recipe_detail) {
    @Inject
    lateinit var bookmarkController: BookmarkController
    @Inject
    lateinit var mainNavigationHandler: MainNavigationHandler
    private val recipeViewModel: RecipeViewModel by activityViewModels()

    private lateinit var veganTypesKr:Array<String>
    private lateinit var veganTypesEng:Array<String>

    private var typeface: Typeface? = null

    override fun init() {
        isCancelable = false
        binding.lifecycleOwner = this
        typeface = ResourcesCompat.getFont(requireContext(), R.font.pretendard_regular)

        recipeViewModel.recipeDetailData.observe(this){
            setBinding(it)
        }

        setBtnClose()
        onBackPressed()
    }

    private fun setBtnClose(){
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun setVeganType(type:String):String{
        veganTypesKr = resources.getStringArray(R.array.vegan_type)
        veganTypesEng = resources.getStringArray(R.array.vegan_types_eng)

        val index = veganTypesEng.indexOf(type)
        return veganTypesKr[index]
    }

    private fun setBinding(data: TipsRecipeDetail){
        binding.tvRecipeTitle.text = data.name
        binding.tvVeganType.text = setVeganType(data.veganType)
        setIngredients(data.ingredients)
        setProcess(data.blocks)

        binding.tbInterest.setOnCheckedChangeListener(null)
        binding.tbInterest.isChecked = data.isBookmarked

        binding.tbInterest.setOnCheckedChangeListener { _, isChecked ->
//            recipeViewModel.setSelectedTbIsChecked(isChecked)
            if(isChecked){
                setSnackBar(getString(R.string.toast_scrap_done))
            }else{
                setSnackBar(getString(R.string.toast_scrap_undo))
            }
        }
    }

    private fun setIngredients(list: List<RecipeIngredient>){
        val parentLayout = binding.llIngredients
        parentLayout.removeAllViews()

        for (item in list){
            val textView = TextView(context).apply {
                text = "• ${item.name}"
                textSize = 14f
                setTextColor(ContextCompat.getColor(context, R.color.color_text_01))
            }
            textView.typeface = typeface
            parentLayout.addView(textView)
        }
    }
    private fun setProcess(list: List<RecipeBlock>){
        val parentLayout = binding.llProcess
        parentLayout.removeAllViews()

        for (item in list){
            val textView = TextView(context).apply {
                text = "${item.sequence}. ${item.content}"
                textSize = 14f
                setTextColor(ContextCompat.getColor(context, R.color.color_text_01))
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 16
                }
            }
            textView.typeface = typeface
            parentLayout.addView(textView)
        }
    }

    private fun onBackPressed(){
        dialog?.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.action == KeyEvent.ACTION_UP) {
                Timber.d("dialog onBackPressed: dismiss()")
                dismiss()
                true
            } else {
                false
            }
        }
    }

    private fun setSnackBar(message:String){
        val snackbar = Snackbar.make(binding.clLayout, message, Snackbar.LENGTH_SHORT)
            .setAction(getString(R.string.toast_scrap_action)){
                mainNavigationHandler.navigateTipsMagazineToMyMagazine()
            }
            .setActionTextColor(resources.getColor(R.color.color_primary_variant_02))
        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTypeface(typeface)
        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action).setTypeface(typeface)
        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as FrameLayout.LayoutParams
        params.bottomMargin = 200
        snackbarView.layoutParams = params

        snackbar.show()
    }
}