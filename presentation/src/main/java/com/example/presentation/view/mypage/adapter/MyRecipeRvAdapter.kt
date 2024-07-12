package com.example.presentation.view.mypage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.mypage.MypageMyRecipeItem
import com.example.presentation.R
import com.example.presentation.databinding.ItemRecipeBinding

class MyRecipeRvAdapter(private val context: Context, private val list:List<MypageMyRecipeItem>
) : RecyclerView.Adapter<MyRecipeRvAdapter.RecyclerViewHolder>(){
    private var listener: OnItemClickListener? = null
    private lateinit var veganTypesKr:Array<String>
    private lateinit var veganTypesEng:Array<String>

    inner class RecyclerViewHolder(private val binding: ItemRecipeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int):CompoundButton {
            val levels = listOf(
                binding.tbVeganLevelMilk,
                binding.tbVeganLevelEgg,
                binding.tbVeganLevelFish,
                binding.tbVeganLevelChicken,
                binding.tbVeganLevelMeat
            )

            val item = list[position]
            binding.tvRecipeName.text = item.name
            binding.tvVeganType.text = setVeganType(item.veganType, levels)

            binding.tbInterest.setOnCheckedChangeListener(null)
            binding.tbInterest.isChecked = true
            binding.tbInterest.setOnCheckedChangeListener { _, isChecked ->
                listener?.setToggleButton(isChecked, item.foodId)
            }
            return binding.tbInterest
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(context))
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val toggleButton = holder.bind(position)
        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(list[position].foodId, toggleButton)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(magazineId:Int, toggleButton: CompoundButton)
        fun setToggleButton(isChecked:Boolean, recipeId:Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    private fun setVeganType(type:String, levels:List<CompoundButton>):String{
        veganTypesKr = context.resources.getStringArray(R.array.vegan_type)
        veganTypesEng = context.resources.getStringArray(R.array.vegan_types_eng)

        val index = veganTypesEng.indexOf(type)
        setVeganIcon(index, levels)
        return veganTypesKr[index]
    }

    private fun setVeganIcon(index: Int, levels:List<CompoundButton>){
        for (i in 0..4) {
            when(index-1){
                0 -> levels[i].isChecked = false
                1 -> levels[i].isChecked = i<index-1
                2 -> levels[i].isChecked = i==1
                else -> levels[i].isChecked = i<index-2
            }
        }
    }
}