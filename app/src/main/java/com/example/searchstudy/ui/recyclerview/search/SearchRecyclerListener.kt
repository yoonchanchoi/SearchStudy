package com.example.searchstudy.ui.recyclerview.search

import android.view.View

interface SearchRecyclerListener {

    fun onItemDelete(position: Int)

    fun onItemClick(position: Int)
}