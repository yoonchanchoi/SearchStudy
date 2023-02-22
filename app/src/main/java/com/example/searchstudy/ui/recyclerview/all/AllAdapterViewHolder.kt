package com.example.searchstudy.ui.recyclerview.all

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.network.models.response.ResultSearchAll
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter
import com.example.searchstudy.util.Constants


class AllAdapterViewHolder(private val binding: AllRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(resultSearchAll: ResultSearchAll) {
        if (resultSearchAll.category == "VIEW") {
            Log.e("cyc","resultSearchAll-->${resultSearchAll}")
            Log.e("cyc","")
            Log.e("cyc","---------------시작----------------")
            for(i in resultSearchAll.allItems.indices){
                Log.e("cyc", "resultSearchAll.allItems[$i]--->${resultSearchAll.allItems[i].type}")
            }
            Log.e("cyc","---------------끝----------------")
            Log.e("cyc","")
            binding.tvCatagory.text = resultSearchAll.category
            binding.rvIn.apply {
                this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
                layoutManager = LinearLayoutManager(binding.root.context)
                this.adapter = ViewAdapter().apply {
                    val expressDic = resultSearchAll.allItems.map { it.type=Constants.ALLITEMS } as ArrayList<AllItem>
                    setData(expressDic)
                //                    setData(resultSearchAll.allItems)
                }
            }
        } else {
//            Log.e("cyc","resultSearchAll-->${resultSearchAll}")
//            Log.e("cyc","")
//            Log.e("cyc","---------------시작----------------")
//            for(i in resultSearchAll.allItems.indices){
//                Log.e("cyc", "resultSearchAll.allItems[$i]--->${resultSearchAll.allItems[i]}")
//            }
//            Log.e("cyc","---------------끝----------------")
//            Log.e("cyc","")

            binding.tvCatagory.text = resultSearchAll.category
            binding.rvIn.apply {
                this.addItemDecoration(DividerItemDecorator(context.resources.getDrawable(com.example.searchstudy.R.drawable.all_item_stroke,null)))
                layoutManager = LinearLayoutManager(binding.root.context)
                this.adapter = DictionaryAdapter().apply {
                    val expressDic = resultSearchAll.allItems.map { it.type= Constants.ALLITEMS } as ArrayList<AllItem>
                    setData(expressDic)
//                    setData(resultSearchAll.allItems)

                }
            }
        }
    }
}
