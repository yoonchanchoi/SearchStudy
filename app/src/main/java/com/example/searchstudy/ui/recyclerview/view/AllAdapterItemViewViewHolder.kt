package com.example.searchstudy.ui.recyclerview.view


import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemViewBinding
import com.example.searchstudy.network.models.response.AllItems


class AllAdapterItemViewViewHolder(private val binding: AllRecyclerviewItemViewBinding) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(allItems: AllItems) {
        binding.tvTitle.text = Html.fromHtml(allItems.title)
        if(allItems.postdate.isEmpty()){
            binding.tvCatagory.text = "카페"
            binding.tvName.text = allItems.cafename
        }else{
            binding.tvName.text = allItems.bloggername
            binding.tvCatagory.text = "블로그"
        }
        binding.tvDescription.text = Html.fromHtml(allItems.description)
    }






}
