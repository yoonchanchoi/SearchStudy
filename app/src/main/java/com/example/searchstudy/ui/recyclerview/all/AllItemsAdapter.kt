package com.example.searchstudy.ui.recyclerview.all

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.*
import com.example.searchstudy.network.models.response.ResultSearchAll
import kotlin.collections.ArrayList

//class AllItemsAdapter(private val context: Context, private val allChildRecyclerListener: AllChildRecyclerListener) : RecyclerView.Adapter<AllItemViewHolder>() {
class AllItemsAdapter(private val context: Context, private val listener: (link: String) -> Unit) :
    RecyclerView.Adapter<AllItemViewHolder>() {


    private val arrayResultSearchAllData = mutableListOf<ResultSearchAll>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllItemViewHolder {
        val itemBinding =
            AllRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllItemViewHolder(context, itemBinding)
    }

    override fun onBindViewHolder(holder: AllItemViewHolder, position: Int) {
        holder.bind(arrayResultSearchAllData[position], listener)
//        holder.bind(arrayResultSearchAllData[position],allChildRecyclerListener)
    }

    override fun getItemCount(): Int {
        return arrayResultSearchAllData.size
    }

    fun setData(arrayResultSearchAll: ArrayList<ResultSearchAll>) {
        arrayResultSearchAll?.let {
            arrayResultSearchAllData.addAll(it)
        }
        notifyDataSetChanged()
    }
}
