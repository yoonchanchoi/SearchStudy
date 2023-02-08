package com.example.searchstudy.ui.recyclerview.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemDictionaryBinding
import com.example.searchstudy.databinding.AllRecyclerviewItemViewBinding
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.databinding.ViewRecyclerviewItemBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.network.models.response.AllItems
import com.example.searchstudy.ui.recyclerview.dictionary.AllAdapterItemDictionaryViewHolder
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryViewHolder

class ViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var viewItems = mutableListOf<AllItems>()
    var viewIntegrated = Integrated()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val viewRecyclerviewItemBinding = ViewRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ViewViewHolder(viewRecyclerviewItemBinding)
            }
            else -> {
                val allRecyclerviewItemDictionaryBinding = AllRecyclerviewItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AllAdapterItemViewViewHolder(allRecyclerviewItemDictionaryBinding)
            }
        }
//        val itemBinding = ViewRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (viewIntegrated.type) {
            1 -> {
                (holder as ViewViewHolder).bind(viewItems[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as AllAdapterItemViewViewHolder).bind(viewItems[position])
                holder.setIsRecyclable(false)
            }

        }
//        holder.bind(viewItems[position])
    }

    override fun getItemCount(): Int {
        return viewItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewIntegrated.type
    }

    fun removeAll() {
        viewItems.clear()
        notifyDataSetChanged()
    }

    fun setData(data: Integrated) {
        viewIntegrated = data
        data.allItemsarraylist?.let {
            viewItems = it
        }
        notifyDataSetChanged()
    }
}