package com.example.searchstudy.ui.recyclerview.dictionary


import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemDictionaryBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.network.models.response.AllItems


class AllAdapterItemDictionaryViewHolder(private val binding: AllRecyclerviewItemDictionaryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(allItems: AllItems) {
        binding.tvTitle.text = Html.fromHtml(allItems.title)
        binding.tvLink.text = allItems.link
        binding.tvDescription.text = Html.fromHtml(allItems.description)

    }

}
