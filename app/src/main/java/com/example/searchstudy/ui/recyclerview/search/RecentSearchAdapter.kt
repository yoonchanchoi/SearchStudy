package com.example.searchstudy.ui.recyclerview.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.RecentSearchRecyclerviewItemBinding
import com.example.searchstudy.network.models.dto.searchDto.SearchData

class RecentSearchAdapter(private val searchRecyclerListener: RecentSearchRecyclerListener, private val searchData: ArrayList<SearchData>) :  RecyclerView.Adapter<RecentSearchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val itemBinding = RecentSearchRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return RecentSearchViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        holder.bind(searchData[position], searchRecyclerListener)
    }

    override fun getItemCount(): Int {
        return searchData.size
    }
}