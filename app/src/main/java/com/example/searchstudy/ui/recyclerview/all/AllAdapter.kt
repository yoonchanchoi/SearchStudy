package com.example.searchstudy.ui.recyclerview.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.*
import com.example.searchstudy.network.models.response.ResultSearchAll
import kotlin.collections.ArrayList

class AllAdapter : RecyclerView.Adapter<AllAdapterViewHolder>() {

    private var arrayIntegratedData = mutableListOf<ResultSearchAll>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllAdapterViewHolder {

        val itemBinding =
            AllRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AllAdapterViewHolder, position: Int) {
        holder.bind(arrayIntegratedData[position])
    }

    override fun getItemCount(): Int {
        return arrayIntegratedData.size
    }

    fun setData(arrayIntegrated: ArrayList<ResultSearchAll>) {
        arrayIntegratedData = arrayIntegrated
        notifyDataSetChanged()
    }
}
