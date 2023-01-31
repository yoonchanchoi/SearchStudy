package com.example.searchstudy.ui.recyclerview.search

import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.RecentSearchRecyclerviewItemBinding
import com.example.searchstudy.network.models.dto.searchDto.SearchData

class SearchViewHolder(private val binding: RecentSearchRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {



    fun bind(searchData: SearchData,searchRecyclerListener: SearchRecyclerListener){
        binding.tvSearchItemText.text=searchData.searchText
        binding.tvSearchItemDate.text=searchData.searchTime
        binding.btnDeletItem.setOnClickListener { searchRecyclerListener.onItemDelete(adapterPosition) }
        binding.clSearchItem.setOnClickListener{ searchRecyclerListener.onItemClick(adapterPosition) }
    }


}