package com.example.searchstudy.ui.recyclerview.dictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemDictionaryBinding
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.network.models.response.AllItems

class DictionaryAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dictionaryIntegrated = Integrated()
    var dictionaryItems = mutableListOf<AllItems>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                val dictionaryRecyclerviewItemBinding = DictionaryRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DictionaryViewHolder(dictionaryRecyclerviewItemBinding)
            }
            else -> {
                val allRecyclerviewItemDictionaryBinding =
                    AllRecyclerviewItemDictionaryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                AllAdapterItemDictionaryViewHolder(allRecyclerviewItemDictionaryBinding)
            }
        }

//        val itemBinding = DictionaryRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return DictionaryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (dictionaryIntegrated.type) {
            1 -> {
                (holder as DictionaryViewHolder).bind(dictionaryItems[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as AllAdapterItemDictionaryViewHolder).bind(dictionaryItems[position])
                holder.setIsRecyclable(false)
            }
        }

        //        holder.bind(dictionaryItems[position])
    }

    override fun getItemCount(): Int {
        return dictionaryItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return dictionaryIntegrated.type
    }

    fun setData(data: Integrated) {
        dictionaryIntegrated = data
        data.allItemsarraylist?.let {
            dictionaryItems = it
        }
        notifyDataSetChanged()
    }
}