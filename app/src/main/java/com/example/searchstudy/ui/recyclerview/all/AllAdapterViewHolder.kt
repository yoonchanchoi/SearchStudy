package com.example.searchstudy.ui.recyclerview.all

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.ResultSearchAll



class AllAdapterViewHolder(private val binding: AllRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(resultSearchAll: ResultSearchAll) {
        if (resultSearchAll.category == "VIEW") {
            binding.tvCatagory.text = resultSearchAll.category
        } else {
            binding.tvCatagory.text = resultSearchAll.category
        }
        binding.rvIn.apply {
            this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
            layoutManager = LinearLayoutManager(binding.root.context)
            this.adapter = AllItemAdapter().apply {
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

