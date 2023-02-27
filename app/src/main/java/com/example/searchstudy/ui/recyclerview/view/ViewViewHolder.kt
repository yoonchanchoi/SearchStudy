package com.example.searchstudy.ui.recyclerview.view

import android.content.Context
import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.R
import com.example.searchstudy.databinding.ViewRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.AllItem

class ViewViewHolder(private val context: Context, private val binding: ViewRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    // view뷰홀더 바인드
    fun bind(allItems: AllItem,viewItemRecyclerListener: ViewAdapter.ViewItemRecyclerListener) {
        binding.tvTitle.text = Html.fromHtml(allItems.title,Html.FROM_HTML_MODE_LEGACY)
        if(allItems.postdate.isEmpty()){
            binding.tvCatagory.text = context.getString(R.string.catagory_cafa)
            binding.tvName.text = allItems.cafename
        }else{
            binding.tvName.text = allItems.bloggername
            binding.tvCatagory.text = context.getString(R.string.catagory_blog)
        }
        binding.tvDescription.text = Html.fromHtml(allItems.description,Html.FROM_HTML_MODE_LEGACY)
        binding.cvItem.setOnClickListener{viewItemRecyclerListener.onItemClick(allItems.link)}
    }
}