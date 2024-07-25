package com.example.presentation.view.tips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TipsRecipeListItem
import com.example.presentation.R
import com.example.presentation.databinding.ItemRecipeBinding

class TipsRecipeRvAdapter(private val context: Context,private val list:List<TipsRecipeListItem>): RecyclerView.Adapter<TipsRecipeRvAdapter.RecyclerViewHolder>() {
    private var listener: OnItemClickListener? = null
    private lateinit var veganTypesKr:Array<String>
    private lateinit var veganTypesEng:Array<String>

    inner class RecyclerViewHolder(private val binding: ItemRecipeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position:Int): CompoundButton{
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
            binding.tbInterest.isChecked = item.isBookmarked

            binding.tbInterest.setOnCheckedChangeListener { buttonView, isChecked ->
                listener?.changeBookmark(buttonView, isChecked, item)
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
            holder.itemView.setOnClickListener{
                listener?.onItemClick(list[position].id, toggleButton)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(item:TipsRecipeListItem, position: Int)
        fun changeBookmark(toggleButton: CompoundButton, isBookmarked: Boolean, data: TipsRecipeListItem)
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