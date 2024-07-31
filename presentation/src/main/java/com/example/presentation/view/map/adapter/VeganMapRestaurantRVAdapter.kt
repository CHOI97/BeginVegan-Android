package com.example.presentation.view.map.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.domain.model.map.HistorySearch
import com.example.domain.model.map.VeganMapRestaurant
import com.example.presentation.databinding.ItemRestaurantBinding
import com.example.presentation.databinding.ItemSearchHistoryBinding


class VeganMapRestaurantRVAdapter :
    ListAdapter<VeganMapRestaurant, RecyclerView.ViewHolder>(diffUtil) {


    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(data: VeganMapRestaurant)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, data: VeganMapRestaurant) {
            Glide.with(context).load(data.thumbnail).skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.ivRestaurantImg)
            binding.tvRestaurantName.text = data.name
            binding.tvRestaurantType.text = data.type
            binding.tvHowFar.text = data.distance.toString()
            binding.tvRating.text = data.rate.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val context = holder.itemView.context
        (holder as ViewHolder).bind(context, getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<VeganMapRestaurant>() {

            override fun areItemsTheSame(
                oldItem: VeganMapRestaurant,
                newItem: VeganMapRestaurant
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: VeganMapRestaurant,
                newItem: VeganMapRestaurant
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}