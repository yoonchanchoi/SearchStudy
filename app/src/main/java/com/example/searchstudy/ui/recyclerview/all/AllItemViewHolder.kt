package com.example.searchstudy.ui.recyclerview.all

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.R
import com.example.searchstudy.databinding.AllRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.ResultSearchAll
import com.example.searchstudy.ui.recyclerview.all.child.AllChildItemAdapter


class AllItemViewHolder(private val context: Context, private val binding: AllRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(resultSearchAll: ResultSearchAll, listener: (link: String) -> Unit) {
//    fun bind(resultSearchAll: ResultSearchAll, allChildRecyclerListener:AllChildRecyclerListener) {
        if (resultSearchAll.category == context.getString(R.string.tab_title_view)) {
            binding.tvCatagory.text = resultSearchAll.category
        } else {
            binding.tvCatagory.text = resultSearchAll.category
        }
        binding.rvIn.apply {
            this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
            layoutManager = LinearLayoutManager(binding.root.context)
//            this.adapter = AllChildItemAdapter(context,allChildRecyclerListener).apply {
            this.adapter = AllChildItemAdapter(context,listener).apply {
                setData(resultSearchAll.allItems)
            }
        }
    }
}















    //        if (resultSearchAll.category == "VIEW") {
//            binding.tvCatagory.text = resultSearchAll.category
//            binding.rvIn.apply {
//                this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
//                layoutManager = LinearLayoutManager(binding.root.context)
//                this.adapter = ViewAdapter().apply {
////                    val expressDic = resultSearchAll.allItems.map { it.type=Constants.ALLITEMS } as ArrayList<AllItem>
////                    setData(expressDic)
//                    setData(resultSearchAll.allItems)
//                }
//            }
//        } else {
//            binding.tvCatagory.text = resultSearchAll.category
//            binding.rvIn.apply {
//                this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
//                layoutManager = LinearLayoutManager(binding.root.context)
//                this.adapter = DictionaryAdapter().apply {
////                    val expressDic = resultSearchAll.allItems.map { it.type= Constants.ALLITEMS } as ArrayList<AllItem>
////                    setData(expressDic)
//                    setData(resultSearchAll.allItems)
//
//                }
//            }
//        }

