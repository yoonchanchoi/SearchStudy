package com.example.searchstudy.ui.recyclerview.view

import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemViewBinding
import com.example.searchstudy.databinding.ViewRecyclerviewItemBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.network.models.response.AllItems

class ViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //view의 arraylsit 아이템
    var viewItems = mutableListOf<AllItems>()
    //viewIntegrated 객체
    var viewIntegrated = Integrated()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> {
                //뷰 탭의 view뷰홀더 레이아웃
                val viewRecyclerviewItemBinding = ViewRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ViewViewHolder(viewRecyclerviewItemBinding)
            }
            //통합 탭의 allview뷰홀더 레이아웃
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
//        Log.e("cyc","전--------data.allItemsarraylist-->${data.allItemsarraylist}")

        data.allItemsarraylist?.let {
            viewItems = it
        }
        notifyDataSetChanged()
    }

    fun addData(data: Integrated){
        viewIntegrated = data
        data.allItemsarraylist?.let {
            viewItems.addAll(it)
        }
        notifyDataSetChanged()
    }
}











//            Log.e("cyc","view---안찍힌다고???")
//            viewItems = it.apply {
//                this.sortWith(compareBy{ allItems ->
//                    allItems.title.apply {
//                        Log.e("cyc","전---문자열--this---${this}")
//                        Html.fromHtml(this,Html.FROM_HTML_MODE_LEGACY).toString()
//                        Log.e("cyc","후---문자열--Html.fromHtml(this,Html.FROM_HTML_MODE_LEGACY).toString()---${Html.fromHtml(this,Html.FROM_HTML_MODE_LEGACY).toString()}")
//                    }
//                    Log.e("cyc","-----정렬----allItems.title->${allItems.title}")
//                })
//            }
//            Log.e("cyc","-----정렬----viewItems->${viewItems}")


//            viewItems.sortBy { allItems ->
//
//                allItems.title.apply {
//                    Log.e("cyc","전---문자열--this---${this}")
//                    Html.fromHtml(this,Html.FROM_HTML_MODE_LEGACY)
//                    Log.e("cyc","후---문자열--this---${this}")
//
//                }
////                allItems.title.apply {
////                    Log.e("cyc","전---문자열--this---${this}")
////                    this.replace(this,(Html.fromHtml(this,Html.FROM_HTML_MODE_LEGACY)).toString())
////                    Log.e("cyc","후---문자열--this---${this}")
////
////                }
//                Log.e("cyc","hteml----제거------allItems.title-->${allItems.title}")
//            }



//    private fun titleHtml(viewItems: ArrayList<AllItems>): ArrayList<AllItems> {
//        val tempArray = ArrayList<AllItems>()
//
//        for (i in 0 until viewItems.size) {
////            val temp = viewItems[i].title.replace(
////                viewItems[i].title,
////                Html.fromHtml(viewItems[i].title, Html.FROM_HTML_MODE_LEGACY).toString()
////            )
////
////            Log.e("jsp",temp)
//////            viewItems[i].title.replace("<b>","")
//////            viewItems[i].title.replace("</b>","")
////            Log.e("cyc","viewItems[i]--->${viewItems[i]}")
////            Log.e("cyc","viewItems[i].title--->${viewItems[i].title}")
////            Log.e("cyc","Html.fromHtml(viewItems[i].title, Html.FROM_HTML_MODE_LEGACY).toString()--->${Html.fromHtml(viewItems[i].title, Html.FROM_HTML_MODE_LEGACY).toString()}")
//
//        }
//        tempArray.addAll(viewItems)
//        Log.e("cyc", "tempArray--->${tempArray}")
//        return tempArray
//
//    }
