package com.example.searchstudy.ui.recyclerview.dictionary

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemDictionaryBinding
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.util.Constants

class DictionaryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dictionaryItems = mutableListOf<AllItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //dictionary의 타입에 따른 통합, 백과사전 뷰홀더 바인딩 구성
        return when (viewType) {
            Constants.ITEMS -> {
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
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //dictionary의 타입에 따른 통합, 백과사전 뷰홀더 구성

        when (dictionaryItems[position].type) {
            Constants.ITEMS -> {
                (holder as DictionaryViewHolder).bind(dictionaryItems[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as AllAdapterItemDictionaryViewHolder).bind(dictionaryItems[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int {
        return dictionaryItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return dictionaryItems[position].type
    }


    fun setData(data: ArrayList<AllItem>) {
        data?.let {
            dictionaryItems = it
        }
        notifyDataSetChanged()
    }
    fun addData(data: ArrayList<AllItem>){
        data?.let {
            dictionaryItems.addAll(it)
        }
        notifyDataSetChanged()
    }

}