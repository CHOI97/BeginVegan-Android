package com.example.presentation.view.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.alarms.Alarm
import com.example.presentation.databinding.ItemNotificationBinding

class NotificationUnreadRvAdapter(private val list:MutableList<Alarm>, private val context: Context):
    RecyclerView.Adapter<NotificationUnreadRvAdapter.RecyclerViewHolder>(){
        private var listener: OnItemClickListener? = null

    inner class RecyclerViewHolder(private val binding: ItemNotificationBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val item = list[position]
            binding.tvBadgeType.text = item.alarmType
            binding.ivBadgeNew.isVisible = true
            binding.tvDate.text = item.createdDate.toString()
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
        if(position!=RecyclerView.NO_POSITION){
            holder.itemView.setOnClickListener {
                listener?.onItemClick(it, list[position], position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(v: View, data:Alarm, position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
}