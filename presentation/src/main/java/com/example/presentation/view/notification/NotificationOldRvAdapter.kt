package com.example.presentation.view.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.notification.Notification
import com.example.presentation.databinding.ItemNotificationBinding

class NotificationOldRvAdapter (private val list:MutableList<Notification>, private val context: Context):
    RecyclerView.Adapter<NotificationOldRvAdapter.RecyclerViewHolder>(){
    private var listener: OnItemClickListener? = null

    inner class RecyclerViewHolder(private val binding: ItemNotificationBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val item = list[position]
            binding.tvBadgeType.text = item.veganType
            binding.ivBadgeNew.isVisible = item.checked
            binding.tvDate.text = item.date
            binding.tvContent.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ItemNotificationBinding.inflate(
            LayoutInflater.from(context)
        )
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(position)
        if(position!= RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(it, list[position], position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(v: View, data: Notification, position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}