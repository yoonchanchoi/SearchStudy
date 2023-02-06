package com.example.searchstudy.ui.recyclerview.all

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemOutBinding
import com.example.searchstudy.databinding.ViewRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.DictionaryItems
import com.example.searchstudy.network.models.response.ImgItems
import com.example.searchstudy.network.models.response.ViewItems
import com.example.searchstudy.ui.recyclerview.view.ViewViewHolder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class OutAllAdapter() : RecyclerView.Adapter<OutAllAdapterViewHolder>() {
//        private val allData  = hashMapOf<String,ArrayList<*>>()
//    companion object {
//        const val VIEW_TYPE_ONE = 1
//        const val VIEW_TYPE_TWO = 2
//    }



    private var data = mutableMapOf<String ,ArrayList<*>>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutAllAdapterViewHolder {

        val itemBinding = AllRecyclerviewItemOutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OutAllAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: OutAllAdapterViewHolder, position: Int) {
//        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(title: String ,arrayList: ArrayList<*>) {
        Log.e("cyc", "ViewAdapter----data---->data")
        data.put(title,arrayList)
        notifyDataSetChanged()
    }
}