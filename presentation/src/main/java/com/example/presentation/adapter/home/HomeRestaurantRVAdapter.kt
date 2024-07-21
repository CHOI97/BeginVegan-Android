package com.example.presentation.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.map.NearRestaurant
import com.example.presentation.databinding.ItemHomeRecommendRestaurantBinding

class HomeRestaurantRVAdapter(
    private val context: Context
) :
    ListAdapter<NearRestaurant, RecyclerView.ViewHolder>(AsyncDifferConfig.Builder(diffUtil).build()) {
    private var listener: OnItemClickListener? = null

    inner class ViewHolder(val binding: ItemHomeRecommendRestaurantBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(data: NearRestaurant){
//            Glide.with(context).load(data.imageUrl).override(Target.SIZE_ORIGINAL)
//                .into(binding.ivItemRestaurantImage)
            binding.tvRestaurantName.text = data.name

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemHomeRecommendRestaurantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        val nearRestaurant = getItem(position) as NearRestaurant
        viewHolder.bind(nearRestaurant)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, data: NearRestaurant, position: Int)

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<NearRestaurant>() {

            override fun areItemsTheSame(
                oldItem: NearRestaurant,
                newItem: NearRestaurant
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: NearRestaurant,
                newItem: NearRestaurant
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}