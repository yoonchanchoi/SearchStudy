package com.example.searchstudy.ui.recyclerview.all

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemOutBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import kotlin.collections.ArrayList

class OutAllAdapter() : RecyclerView.Adapter<OutAllAdapterViewHolder>() {

    private var arrayIntegratedData= mutableListOf<Integrated>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutAllAdapterViewHolder {

        val itemBinding = AllRecyclerviewItemOutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OutAllAdapterViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: OutAllAdapterViewHolder, position: Int) {
        holder.bind(arrayIntegratedData[position])
    }

    override fun getItemCount(): Int {
        return arrayIntegratedData.size
    }

    fun setData(arrayIntegrated:ArrayList<Integrated>) {
        Log.e("cyc", "ViewAdapter----data---->data")
        arrayIntegratedData=arrayIntegrated
        notifyDataSetChanged()
    }
}