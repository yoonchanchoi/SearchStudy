package com.example.searchstudy.ui.recyclerview.dictionary

import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.DictionaryItems

class DictionaryViewHolder(private val binding: DictionaryRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dictionaryItems: DictionaryItems) {
        binding.tvTitle.text = dictionaryItems.title
        binding.tvLink.text = dictionaryItems.link
        binding.tvDescription.text = dictionaryItems.description
    }
}