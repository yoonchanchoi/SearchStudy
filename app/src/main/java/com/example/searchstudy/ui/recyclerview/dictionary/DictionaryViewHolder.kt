package com.example.searchstudy.ui.recyclerview.dictionary

import android.text.Html
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.DictionaryItems

class DictionaryViewHolder(private val binding: DictionaryRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dictionaryItems: DictionaryItems) {
        binding.tvTitle.text = Html.fromHtml(dictionaryItems.title)
        binding.tvLink.text = dictionaryItems.link
        binding.tvDescription.text = Html.fromHtml(dictionaryItems.description)
    }
}