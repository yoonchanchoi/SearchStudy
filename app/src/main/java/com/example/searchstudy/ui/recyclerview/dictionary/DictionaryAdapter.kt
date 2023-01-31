package com.example.searchstudy.ui.recyclerview.dictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.DictionaryItems

class DictionaryAdapter(private val dictionaryItems: ArrayList<DictionaryItems>) : RecyclerView.Adapter<DictionaryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        val itemBinding = DictionaryRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DictionaryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        holder.bind(dictionaryItems[position])
    }

    override fun getItemCount(): Int {
        return dictionaryItems.size
    }
}