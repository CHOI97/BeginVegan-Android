package com.example.presentation.view.tips.view

import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.domain.model.RecipeBlock
import com.example.domain.model.RecipeIngredient
import com.example.domain.model.TipsRecipeDetail
import com.example.presentation.R
import com.example.presentation.base.BaseDialogFragment
import com.example.presentation.databinding.DialogRecipeDetailBinding
import com.example.presentation.util.BookmarkController
import com.example.presentation.view.tips.viewModel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TipsRecipeDetailDialog:BaseDialogFragment<DialogRecipeDetailBinding>(R.layout.dialog_recipe_detail) {
    @Inject
    lateinit var bookmarkController: BookmarkController
    private val recipeViewModel: RecipeViewModel by activityViewModels()

    private lateinit var veganTypesKr:Array<String>
    private lateinit var veganTypesEng:Array<String>

    override fun init() {
        isCancelable = false
        binding.lifecycleOwner = this

        recipeViewModel.recipeDetailData.observe(this){
            setBinding(it)
        }

        setBtnClose()
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
            recipeViewModel.setSelectedTbIsChecked(isChecked)
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
            parentLayout.addView(textView)
        }
    }
}