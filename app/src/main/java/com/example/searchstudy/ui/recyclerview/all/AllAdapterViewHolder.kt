package com.example.searchstudy.ui.recyclerview.all

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter


class AllAdapterViewHolder(private val binding: AllRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(integrated: Integrated) {
        Log.e("cyc", "전체---integrated--->${integrated}")
        if (integrated.title == "VIEW") {
            binding.tvCatagory.text = integrated.title
            binding.rvIn.apply {
                this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
                layoutManager = LinearLayoutManager(binding.root.context)
                this.adapter = ViewAdapter().apply {
                    setData(integrated)
                }
            }
        } else {
            binding.tvCatagory.text = integrated.title
            binding.rvIn.apply {
                this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
                layoutManager = LinearLayoutManager(binding.root.context)
                this.adapter = DictionaryAdapter().apply {

                    setData(integrated)
                }
            }
        }
    }
}
