package com.example.searchstudy.ui.recyclerview.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.RecentSearchRecyclerviewItemBinding
import com.example.searchstudy.network.models.dto.search.SearchData

class SearchAdapter(private val searchRecyclerListener: SearchRecyclerListener, private val searchData: ArrayList<SearchData>) :  RecyclerView.Adapter<SearchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemBinding = RecentSearchRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return SearchViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchData[position], searchRecyclerListener)
    }

    override fun getItemCount(): Int {
        return searchData.size
    }

}