package com.example.searchstudy.ui.recyclerview.search

interface SearchRecyclerListener {

    fun onItemDelete(position: Int)

    fun onItemClick(position: Int)
}