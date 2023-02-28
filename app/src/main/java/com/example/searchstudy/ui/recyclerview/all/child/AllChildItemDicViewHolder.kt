package com.example.searchstudy.ui.recyclerview.all.child


import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemDictionaryBinding
import com.example.searchstudy.network.models.response.AllItem


class AllChildItemDicViewHolder(private val binding: AllRecyclerviewItemDictionaryBinding) :
    RecyclerView.ViewHolder(binding.root) {

//    fun bind(allItems: AllItem, allChildRecyclerListener: AllChildRecyclerListener) {
    fun bind(allItems: AllItem, listener: (link: String) -> Unit) {

        binding.tvTitle.text = Html.fromHtml(allItems.title, Html.FROM_HTML_MODE_LEGACY)
        binding.tvDescription.text = Html.fromHtml(allItems.description, Html.FROM_HTML_MODE_LEGACY)

        binding.clAllDic.setOnClickListener { listener(allItems.link) }
//        binding.clAllDic.setOnClickListener { allChildRecyclerListener.onItemClick(allItems.link)}

    }
}
