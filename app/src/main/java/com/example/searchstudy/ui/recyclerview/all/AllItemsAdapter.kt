package com.example.searchstudy.ui.recyclerview.all

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.*
import com.example.searchstudy.network.models.response.ResultNaver
import com.example.searchstudy.network.models.response.ResultPapago
import com.example.searchstudy.network.models.response.ResultSearchAll
import com.example.searchstudy.util.Constants
import kotlin.collections.ArrayList


//class AllItemsAdapter(private val context: Context, private val allChildRecyclerListener: AllChildRecyclerListener) : RecyclerView.Adapter<AllItemViewHolder>() {
class AllItemsAdapter(private val context: Context, private val listener: (link: String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val arrayResultNaver = mutableListOf<ResultNaver>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Constants.SEARCH -> {
                val allRecyclerviewItemViewBinding = AllRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AllItemViewHolder(context, allRecyclerviewItemViewBinding)
            }
            else -> {
                val translationViewBinding = AllTranslationRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AllTranslationViewHolder(translationViewBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (arrayResultNaver[position].classType) {
            Constants.SEARCH -> {
                (holder as AllItemViewHolder).bind(arrayResultNaver[position] as ResultSearchAll, listener)
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as AllTranslationViewHolder).bind(arrayResultNaver[position] as ResultPapago)
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayResultNaver.size
    }

    override fun getItemViewType(position: Int): Int {
        return arrayResultNaver[position].classType
    }

    fun setData(arrayResultNaver: ArrayList<ResultNaver>) {
        arrayResultNaver?.let {
            arrayResultNaver.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun addData(resultNaver: ResultNaver) {
        Log.e("cyc","")
        Log.e("cyc","-----------------------------------")
        Log.e("cyc","resultNaver--->${resultNaver}")
        Log.e("cyc","-----------------------------------")
        Log.e("cyc","")
        arrayResultNaver.add(0, resultNaver)
        notifyDataSetChanged()
    }
}


////class AllItemsAdapter(private val context: Context, private val allChildRecyclerListener: AllChildRecyclerListener) : RecyclerView.Adapter<AllItemViewHolder>() {
//class AllItemsAdapter(private val context: Context, private val listener: (link: String) -> Unit) :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//
//    private val arrayResultSearchAllData = mutableListOf<ResultSearchAll>()
//    private var resultPapago = ResultPapago()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if (resultPapago != null) {
//            val allTranslationRecyclerviewItemBinding =
//                AllTranslationRecyclerviewItemBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//            TranslationViewHolder(allTranslationRecyclerviewItemBinding)
//        }
//        val allRecyclerviewItemBinding =
//            AllRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return AllItemViewHolder(context, allRecyclerviewItemBinding)
//
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//        (holder as TranslationViewHolder).bind(resultPapago)
//        holder.setIsRecyclable(false)
//        (holder as AllItemViewHolder).bind(arrayResultSearchAllData[position],listener)
//        holder.setIsRecyclable(false)
////        if(adatperResultPapago!=null){
////            holder.bind(arrayResultSearchAllData[position], listener)
////
////        }else{
////            holder.translationBind()
////            holder.bind(arrayResultSearchAllData[position], listener)
////
////        }
////        holder.bind(arrayResultSearchAllData[position], listener)
////        holder.bind(arrayResultSearchAllData[position],allChildRecyclerListener)
//    }
//
//    override fun getItemCount(): Int {
//        return arrayResultSearchAllData.size
//    }
//
////    override fun getItemViewType(position: Int): Int {
////        return adatperResultPapago
////    }
//
//    fun setData(arrayResultSearchAll: ArrayList<ResultSearchAll>) {
//        arrayResultSearchAll?.let {
//            arrayResultSearchAllData.addAll(it)
//        }
//        notifyDataSetChanged()
//    }
//
//    fun setPapage(resultPapago: ResultPapago){
//        this.resultPapago= resultPapago
//
//    }
//}