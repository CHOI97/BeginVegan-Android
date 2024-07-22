package com.example.presentation.view.map.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchAdapterDataObserver(rv: RecyclerView?, tv: TextView?) :
    RecyclerView.AdapterDataObserver() {
    private var emptyView: TextView? = null
    private var recyclerView: RecyclerView? = null

    init {
        recyclerView = rv
        emptyView = tv
        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        if (emptyView != null && recyclerView!!.adapter != null) {
            val emptyViewVisible = recyclerView!!.adapter!!.itemCount == 0
            emptyView!!.visibility = if (emptyViewVisible)
                View.VISIBLE else View.INVISIBLE
            recyclerView!!.visibility = if (emptyViewVisible)
                View.INVISIBLE else View.VISIBLE
        }
    }

    // 데이터의 변화에 따라 호출되는 메소드
    override fun onChanged() {
        super.onChanged()
        checkIfEmpty()
    }
}