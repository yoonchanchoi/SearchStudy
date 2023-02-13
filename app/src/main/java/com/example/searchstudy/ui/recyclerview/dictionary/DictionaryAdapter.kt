package com.example.searchstudy.ui.recyclerview.dictionary

import android.text.Html
import android.util.Log
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
//            dictionaryItems.sortWith(compareBy { allItems ->
//                val tagExcept = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>"
//                val match = "[^\uAC00-\uD7A30-9a-zA-Z\\s]"
//                val tagExceptTempStr = allItems.title.replace(Regex(tagExcept),"")
//                val matchTempStr = tagExceptTempStr.replace(Regex(match),"")
//                Log.e("cyc","tempStr--->${matchTempStr}")
//                Html.fromHtml(matchTempStr, Html.FROM_HTML_MODE_LEGACY).toString()
//            })
        }
        notifyDataSetChanged()
    }
    fun addData(data: Integrated){
        dictionaryIntegrated = data
        data.allItemsarraylist?.let {
            dictionaryItems.addAll(it)
        }
        notifyDataSetChanged()
    }

}