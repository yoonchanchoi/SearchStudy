package com.example.searchstudy.ui.recyclerview.img

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.ImgRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.DictionaryItems
import com.example.searchstudy.network.models.response.ImgItems

class ImgAdapter() : RecyclerView.Adapter<ImgViewHolder>() {


    var imgItems= mutableListOf<ImgItems>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {

        val itemBinding = ImgRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImgViewHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        holder.bind(imgItems[position])
    }
    override fun getItemCount(): Int {
        return imgItems.size
    }

    fun setData(data : ArrayList<ImgItems>){
        imgItems = data
        notifyDataSetChanged()
    }

}