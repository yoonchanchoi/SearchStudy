package com.example.searchstudy.ui.recyclerview.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.*
import com.example.searchstudy.network.models.dto.integrated.Integrated
import kotlin.collections.ArrayList

class AllAdapter() : RecyclerView.Adapter<AllAdapterViewHolder>() {

    private var arrayIntegratedData = mutableListOf<Integrated>()
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

    fun setData(arrayIntegrated: ArrayList<Integrated>) {
        arrayIntegratedData = arrayIntegrated
        notifyDataSetChanged()
    }
}


//        when(viewType){
//            1->{
//                val allRecyclerItemBinding = AllRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//                return AllAdapterViewHolder(allRecyclerItemBinding)
//
//            }
//            2->{
//                val allRecyclerviewItemViewBinding = AllRecyclerviewItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//                return AllAdapterViewItemViewHolder(allRecyclerviewItemViewBinding)
//            }
//            else ->{
//                val allRecyclerviewItemDictionaryBinding = AllRecyclerviewItemDictionaryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//                return AllAdapterDictionaryItemViewHolder(allRecyclerviewItemDictionaryBinding)
//            }
//        }

//        when(arrayIntegratedData[position].type){
//            1->{
//                (holder as AllAdapterViewHolder).bind(arrayIntegratedData[position])
//                holder.setIsRecyclable(false)
//            }
//            2->{
//                (holder as AllAdapterViewItemViewHolder).bind(arrayIntegratedData[position])
//                holder.setIsRecyclable(false)
//            }
//            else->{
//                (holder as AllAdapterDictionaryItemViewHolder).bind(arrayIntegratedData[position])
//                holder.setIsRecyclable(false)
//            }
//        }