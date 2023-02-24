package com.example.searchstudy.ui.recyclerview.search

import android.provider.ContactsContract
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.RecentSearchRecyclerviewItemBinding
import com.example.searchstudy.network.models.dto.searchDto.SearchData
import java.text.SimpleDateFormat
import java.util.*

class SearchViewHolder(private val binding: RecentSearchRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(searchData: SearchData, searchRecyclerListener: SearchRecyclerListener) {
        val dateForm = SimpleDateFormat("YY.MM.dd")
        val timeForm = SimpleDateFormat("HH:mm:ss")
        val currentDate = System.currentTimeMillis()
        val searchDate = searchData.searchTime.toLong()

        binding.tvSearchItemText.text = searchData.searchText
        binding.tvSearchItemDate.text =
            if (dateForm.format(currentDate) == dateForm.format(searchDate)) {
                timeForm.format(searchDate)
            } else {
                dateForm.format(searchDate)
            }
        binding.btnDeletItem.setOnClickListener {
            searchRecyclerListener.onItemDelete(
                bindingAdapterPosition
//                        adapterPosition
            )
        }
        binding.clSearchItem.setOnClickListener { searchRecyclerListener.onItemClick(bindingAdapterPosition) }
    }


}