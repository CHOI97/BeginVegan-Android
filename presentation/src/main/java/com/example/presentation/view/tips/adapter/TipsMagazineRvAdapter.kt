package com.example.presentation.view.tips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.tips.MagazineResponse
import com.example.presentation.databinding.ItemMagazineBinding

class TipsMagazineRvAdapter(private val context:Context,private val list:MutableList<MagazineResponse>):RecyclerView.Adapter<TipsMagazineRvAdapter.RecyclerViewHolder>() {
    private var listener: OnItemClickListener? = null

    inner class RecyclerViewHolder(private val binding:ItemMagazineBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(position:Int){
                val item = list[position]
                binding.tvMagazineTitle.text = item.title
                binding.tvWriter.text = item.editor
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemMagazineBinding.inflate(LayoutInflater.from(context))
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(position)
        if(position != RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener{
                listener?.onItemClick(list[position].id)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(magazineId:Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}