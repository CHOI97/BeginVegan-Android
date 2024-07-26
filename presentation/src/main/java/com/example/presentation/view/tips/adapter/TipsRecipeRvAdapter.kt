package com.example.presentation.view.tips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.tips.TipsRecipeListItem
import com.example.presentation.R
import com.example.presentation.databinding.ItemRecipeBinding
import timber.log.Timber

class TipsRecipeRvAdapter(private val context: Context,private val list:MutableList<TipsRecipeListItem>): RecyclerView.Adapter<TipsRecipeRvAdapter.RecyclerViewHolder>() {
    private var listener: OnItemClickListener? = null
    private lateinit var veganTypesKr:Array<String>
    private lateinit var veganTypesEng:Array<String>

    // DiffUtil
    private val differCallback = object : DiffUtil.ItemCallback<TipsRecipeListItem>() {
        override fun areItemsTheSame(oldItem: TipsRecipeListItem, newItem: TipsRecipeListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TipsRecipeListItem, newItem: TipsRecipeListItem): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    inner class RecyclerViewHolder(private val binding: ItemRecipeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position:Int){
            val levels = listOf(
                binding.tbVeganLevelMilk,
                binding.tbVeganLevelEgg,
                binding.tbVeganLevelFish,
                binding.tbVeganLevelChicken,
                binding.tbVeganLevelMeat
            )

            val item = differ.currentList[position]
            binding.tvRecipeName.text = item.name
            binding.tvVeganType.text = setVeganType(item.veganType, levels)

            binding.tbInterest.setOnCheckedChangeListener(null)
            binding.tbInterest.isChecked = item.isBookmarked

            binding.tbInterest.setOnCheckedChangeListener { buttonView, isChecked ->
                listener?.changeBookmark(buttonView, isChecked, item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(context))
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(position)
        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener{
                listener?.onItemClick(list[position], position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(item: TipsRecipeListItem, position: Int)
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

    fun submitList(newList: List<TipsRecipeListItem>) {
        differ.submitList(newList)
    }
}