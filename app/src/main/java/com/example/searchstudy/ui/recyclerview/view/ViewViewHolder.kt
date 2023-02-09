package com.example.searchstudy.ui.recyclerview.view

import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.ViewRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.AllItems

class ViewViewHolder(private val binding: ViewRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(allItems: AllItems) {
        binding.tvTitle.text = Html.fromHtml(allItems.title,Html.FROM_HTML_MODE_LEGACY)
        if(allItems.postdate.isEmpty()){
            binding.tvCatagory.text = "카페"
            binding.tvName.text = allItems.cafename
        }else{
            binding.tvName.text = allItems.bloggername
            binding.tvCatagory.text = "블로그"
        }
        binding.tvDescription.text = Html.fromHtml(allItems.description,Html.FROM_HTML_MODE_LEGACY)
    }
}