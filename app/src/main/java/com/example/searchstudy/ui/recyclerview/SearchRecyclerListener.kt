package com.example.searchstudy.ui.recyclerview

interface SearchRecyclerListener {

    fun onItemDelete(position: Int)

    fun onItemClick(position: Int)
}