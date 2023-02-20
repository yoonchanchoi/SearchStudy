package com.example.searchstudy.ui.recyclerview.all

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.ResultSearchAll
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter


class AllAdapterViewHolder(private val binding: AllRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(resultSearchAll: ResultSearchAll) {
        if (resultSearchAll.category == "VIEW") {
            binding.tvCatagory.text = resultSearchAll.category
            binding.rvIn.apply {
                this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
                layoutManager = LinearLayoutManager(binding.root.context)
                this.adapter = ViewAdapter().apply {
                    setData(resultSearchAll.allItems)
                }
            }
        } else {
            binding.tvCatagory.text = resultSearchAll.category
            binding.rvIn.apply {
                this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
                layoutManager = LinearLayoutManager(binding.root.context)
                this.adapter = DictionaryAdapter().apply {
                    setData(resultSearchAll.allItems)
                }
            }
        }
    }
}
