package com.example.searchstudy.ui.recyclerview.dictionary

import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.AllItem

class DictionaryViewHolder(private val binding: DictionaryRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(allItems: AllItem, dicItemRecyclerListener : DictionaryAdapter.DicItemRecyclerListener) {
        binding.tvTitle.text = Html.fromHtml(allItems.title,Html.FROM_HTML_MODE_LEGACY)
        binding.tvDescription.text = Html.fromHtml(allItems.description,Html.FROM_HTML_MODE_LEGACY)
        binding.cvItem.setOnClickListener{dicItemRecyclerListener.onItemClick(allItems.link)}
    }

}