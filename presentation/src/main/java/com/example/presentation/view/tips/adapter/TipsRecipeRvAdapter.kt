package com.example.presentation.view.tips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
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
        fun bind(position:Int){
            val levels = listOf(
                { binding.tbVeganLevelMilk.isChecked = true },
                { binding.tbVeganLevelEgg.isChecked = true },
                { binding.tbVeganLevelFish.isChecked = true },
                { binding.tbVeganLevelChicken.isChecked = true },
                { binding.tbVeganLevelMeat.isChecked = true }
            )

            val item = list[position]
            binding.tvRecipeName.text = item.name
            binding.tvVeganType.text = setVeganType(item.veganType, levels)
            binding.tbInterest.isChecked = item.isBookmarked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(context))
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(position)
        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener{
                listener?.onItemClick()
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick()
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    private fun setVeganType(type:String, levels:List<()->Unit>):String{
        veganTypesKr = context.resources.getStringArray(R.array.vegan_type)
        veganTypesEng = context.resources.getStringArray(R.array.vegan_types_eng)

        val index = veganTypesEng.indexOf(type)
        setVeganIcon(index, levels)
        return veganTypesKr[index]
    }

    private fun setVeganIcon(index: Int, levels:List<()->Unit>){
        when(index-1){
            0 -> return
            1 -> levels[0]()
            2 -> levels[1]()
            else -> {
                for (i in 0 until index-1) {
                    levels[i]()
                }
            }
        }
    }
}