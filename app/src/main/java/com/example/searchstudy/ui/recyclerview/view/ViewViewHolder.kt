package com.example.searchstudy.ui.recyclerview.view

import android.text.Html
import android.util.Log
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.databinding.ViewRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.DictionaryItems
import com.example.searchstudy.network.models.response.ViewItems

class ViewViewHolder(private val binding: ViewRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewItems: ViewItems) {
        binding.tvTitle.text = Html.fromHtml(viewItems.title)
        if(viewItems.postdate.isEmpty()){
            binding.tvCatagory.text = "카페"
            binding.tvName.text = viewItems.cafename
        }else{
            binding.tvName.text = viewItems.bloggername
            binding.tvCatagory.text = "블로그"

        }
        binding.tvDescription.text = Html.fromHtml(viewItems.description)
    }
}