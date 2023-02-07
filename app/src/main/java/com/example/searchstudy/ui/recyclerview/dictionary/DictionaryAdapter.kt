package com.example.searchstudy.ui.recyclerview.dictionary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.AllItems

class DictionaryAdapter() : RecyclerView.Adapter<DictionaryViewHolder>() {

    var dictionaryItems= mutableListOf<AllItems>()

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

    fun setData(data : ArrayList<AllItems>){
        dictionaryItems = data
        notifyDataSetChanged()
    }
}