package com.example.presentation.view.notification.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.alarms.Alarm
import com.example.presentation.databinding.ItemNotificationBinding
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class NotificationReadRvAdapter (private val list:List<Alarm>, private val context: Context):
    RecyclerView.Adapter<NotificationReadRvAdapter.RecyclerViewHolder>(){
    private var listener: OnItemClickListener? = null

    inner class RecyclerViewHolder(private val binding: ItemNotificationBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val item = list[position]
            binding.tvBadgeType.text = item.alarmType
            binding.ivBadgeNew.visibility = View.INVISIBLE
            binding.tvDate.text = transferDate(item.createdDate)
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
        fun onItemClick(v: View, data: Alarm, position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    private fun transferDate(date:String):String{
        val stringToDate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        val newDate = LocalDateTime.parse(date, stringToDate)

        val dateToString = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        return newDate.format(dateToString)
    }
}