package com.example.searchstudy.ui.recyclerview.view


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemViewBinding
import com.example.searchstudy.databinding.DictionaryRecyclerviewItemBinding
import com.example.searchstudy.databinding.ViewRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryViewHolder
import com.example.searchstudy.util.Constants
import com.example.searchstudy.util.Util

class ViewAdapter(private val context: Context) : RecyclerView.Adapter<ViewViewHolder>() {


    interface ViewItemRecyclerListener{
        fun onItemClick(link: String)
    }


    private lateinit var viewItemRecyclerListener: ViewItemRecyclerListener
    //view의 arraylsit 아이템
    private val viewItems = mutableListOf<AllItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewViewHolder {
        val viewRecyclerviewItemBinding = ViewRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return ViewViewHolder(context,viewRecyclerviewItemBinding)
        //viewItems의 타입에 따른 통합, View 뷰홀더 바인딩 구성
//        return when (viewType) {
//            Constants.ITEMS -> {
//                //뷰 탭의 view뷰홀더 레이아웃
//                val viewRecyclerviewItemBinding = ViewRecyclerviewItemBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                ViewViewHolder(viewRecyclerviewItemBinding)
//            }
//            //통합 탭의 allview뷰홀더 레이아웃
//            else -> {
//                val allRecyclerviewItemDictionaryBinding = AllRecyclerviewItemViewBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//                AllAdapterItemViewViewHolder(allRecyclerviewItemDictionaryBinding)
//            }
//        }

    }

    override fun onBindViewHolder(holder: ViewViewHolder, position: Int) {
        holder.bind(viewItems[position],viewItemRecyclerListener)
        //viewItems의 타입에 따른 통합, View 뷰홀더 구성
//        when ( viewItems[position].type) {
//            Constants.ITEMS -> {
//                (holder as ViewViewHolder).bind(viewItems[position])
//                holder.setIsRecyclable(false)
//            }
//            else -> {
//                (holder as AllAdapterItemViewViewHolder).bind(viewItems[position])
//                holder.setIsRecyclable(false)
//            }
//        }
    }

    override fun getItemCount(): Int {
        return viewItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewItems[position].type
    }

    fun setData(data: ArrayList<AllItem>) {
        data?.let {
            viewItems.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun addData(data: ArrayList<AllItem>){
        data?.let {
            viewItems.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun setItemClickListener(viewItemRecyclerListener: ViewItemRecyclerListener){
        this.viewItemRecyclerListener = viewItemRecyclerListener
    }
}
































//-----------------------------------------------------------------------------------------------------------------






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
