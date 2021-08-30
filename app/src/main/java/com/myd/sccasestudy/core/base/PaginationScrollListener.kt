package com.myd.sccasestudy.core.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener constructor(private val linearLayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    abstract fun isLoading(): Boolean


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = linearLayoutManager.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisible = linearLayoutManager.findFirstCompletelyVisibleItemPosition()

        if (!isLoading()) {
            if (visibleItemCount + firstVisible >= totalItemCount && firstVisible >= 0) {
                onLoadMore()
            }
        }
    }

    abstract fun onLoadMore()
}