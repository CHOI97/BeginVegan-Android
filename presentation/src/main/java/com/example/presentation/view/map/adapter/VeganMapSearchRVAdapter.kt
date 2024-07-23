package com.example.presentation.view.map.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.map.HistorySearch
import com.example.presentation.databinding.ItemSearchHistoryBinding
import com.example.presentation.databinding.ItemSearchHistoryEmptyBinding

class VeganMapSearchRVAdapter :
    ListAdapter<HistorySearch, RecyclerView.ViewHolder>(diffUtil) {

    private var listener: OnDeleteListener? = null

    interface OnDeleteListener {
        fun onDelete(data: HistorySearch)
    }

    fun setOnItemClickListener(listener: OnDeleteListener) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ItemSearchHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HistorySearch) {
            binding.btnDescription.text = data.description
            binding.btnDelete.setOnClickListener {
                listener?.onDelete(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemSearchHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<HistorySearch>() {

            override fun areItemsTheSame(oldItem: HistorySearch, newItem: HistorySearch): Boolean {
                Log.d("diffutil","oldItem : $oldItem")
                Log.d("diffutil","newItem : $newItem")
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: HistorySearch,
                newItem: HistorySearch
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
