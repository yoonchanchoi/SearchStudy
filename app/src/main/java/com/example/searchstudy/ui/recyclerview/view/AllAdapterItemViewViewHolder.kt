package com.example.searchstudy.ui.recyclerview.view


import android.text.Html
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemViewBinding
import com.example.searchstudy.network.models.response.AllItem


class AllAdapterItemViewViewHolder(private val binding: AllRecyclerviewItemViewBinding) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(allItems: AllItem) {
        binding.tvTitle.text = Html.fromHtml(allItems.title, Html.FROM_HTML_MODE_LEGACY)
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
