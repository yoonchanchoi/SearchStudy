package com.example.searchstudy.ui.recyclerview.all


import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemDictionaryBinding
import com.example.searchstudy.network.models.response.AllItem


class AllAdapterItemDictionaryViewHolder(private val binding: AllRecyclerviewItemDictionaryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(allItems: AllItem, allItemRecyclerListener: AllItemAdapter.AllItemRecyclerListener) {
        binding.tvTitle.text = Html.fromHtml(allItems.title,Html.FROM_HTML_MODE_LEGACY)
        binding.tvDescription.text = Html.fromHtml(allItems.description, Html.FROM_HTML_MODE_LEGACY)

        binding.clAllDic.setOnClickListener { allItemRecyclerListener.onItemClick(bindingAdapterPosition)}
    }
}
