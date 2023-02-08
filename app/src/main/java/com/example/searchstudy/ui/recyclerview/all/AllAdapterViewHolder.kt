package com.example.searchstudy.ui.recyclerview.all

import android.R
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
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
//                val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).orientation)
//                dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider))
//                recyclerView.addItemDecoration(dividerItemDecoration)
//                addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

                this.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL).apply {
                    this.setDrawable(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null))
                })
                layoutManager = LinearLayoutManager(binding.root.context)
                this.adapter = ViewAdapter().apply {
                    setData(integrated)
                }
//변환전
            }
        } else {
            binding.tvCatagory.text = integrated.title
            binding.rvIn.apply {
//                addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
//                addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL).apply {
//                    this.setDrawable(context.resources.getDrawable(R.drawable.all_item_stroke,null))
//                })
                this.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL).apply {
                    this.setDrawable(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null))
                })
                layoutManager = LinearLayoutManager(binding.root.context)
                this.adapter = DictionaryAdapter().apply {
                    setData(integrated)
                }
            }
        }
    }
}
