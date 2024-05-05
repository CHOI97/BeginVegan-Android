package com.example.presentation.view.tips

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemRecipeBinding

class TipsRecipeRvAdapter(private val context: Context): RecyclerView.Adapter<TipsRecipeRvAdapter.RecyclerViewHolder>() {
    private var listener: OnItemClickListener? = null

    inner class RecyclerViewHolder(private val binding: ItemRecipeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position:Int){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(context))
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = 2

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
}