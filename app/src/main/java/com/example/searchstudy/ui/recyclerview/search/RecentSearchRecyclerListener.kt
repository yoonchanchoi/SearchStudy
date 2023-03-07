package com.example.searchstudy.ui.recyclerview.search

interface RecentSearchRecyclerListener {
    fun onItemDelete(position: Int)
    fun onItemClick(position: Int)
}