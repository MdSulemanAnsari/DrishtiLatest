package com.application.drishtigems.Pagination

import androidx.recyclerview.widget.RecyclerView

import androidx.recyclerview.widget.LinearLayoutManager

abstract class PaginationRecycler(var layoutManager: LinearLayoutManager) :  RecyclerView.OnScrollListener(){
    val PAGE_START = 1

    private val PAGE_SIZE = 5

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager?.childCount
        val totalItemCount = layoutManager?.itemCount
        val firstVisibleItemPosition = layoutManager?.findFirstVisibleItemPosition()
        if (!isLoading() && !isLastPage()) {
            if (totalItemCount != null) {
                if (visibleItemCount != null) {
                    if (visibleItemCount + firstVisibleItemPosition!! >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                        loadMoreItems()
                    }
                }
            }
        }
    }

    protected abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}

