package com.example.presentation.view.map.adpater

import android.view.LayoutInflater
import android.view.View
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
            binding.tvDescription.text = data.description
            binding.btnDelete.setOnClickListener {
                listener?.onDelete(data)
            }
        }
    }

    inner class EmptyViewHolder(val binding: ItemSearchHistoryEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> EmptyViewHolder(
                ItemSearchHistoryEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_ITEM -> ViewHolder(
                ItemSearchHistoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            (holder as ViewHolder).bind(getItem(position))
        }
    }

    override fun getItemCount(): Int {
        return if (currentList.isEmpty()) 1 else super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList.isEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_ITEM
    }

    companion object {
        private const val VIEW_TYPE_EMPTY = 0
        private const val VIEW_TYPE_ITEM = 1

        val diffUtil = object : DiffUtil.ItemCallback<HistorySearch>() {

            override fun areItemsTheSame(oldItem: HistorySearch, newItem: HistorySearch): Boolean {
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
