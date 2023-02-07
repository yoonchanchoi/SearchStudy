package com.example.searchstudy.ui.recyclerview.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.ViewRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.AllItems

class ViewAdapter() : RecyclerView.Adapter<ViewViewHolder>() {

    var viewItems= mutableListOf<AllItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewViewHolder {
        val itemBinding = ViewRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewViewHolder, position: Int) {
        holder.bind(viewItems[position])
    }

    override fun getItemCount(): Int {
        return viewItems.size
    }

    fun removeAll(){
        viewItems.clear()
        notifyDataSetChanged()
    }
    fun setData(data : ArrayList<AllItems>){
        viewItems=data
        notifyDataSetChanged()
    }
}