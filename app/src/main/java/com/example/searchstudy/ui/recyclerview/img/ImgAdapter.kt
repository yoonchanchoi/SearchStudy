package com.example.searchstudy.ui.recyclerview.img

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.ImgRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.ImgItems
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter

class ImgAdapter : RecyclerView.Adapter<ImgViewHolder>() {


    interface ImgItemRecyclerListener{
        fun onItemClick(link: String)
    }

    private lateinit var imgItemRecyclerListener : ImgItemRecyclerListener
    private var imgItems = mutableListOf<ImgItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {

        val itemBinding =
            ImgRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImgViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        holder.bind(imgItems[position],imgItemRecyclerListener)
    }

    override fun getItemCount(): Int {
        return imgItems.size
    }

    fun setData(data: ArrayList<ImgItems>) {
        data?.let {
            imgItems=it
        }
//        imgItems.addAll(data)
        notifyDataSetChanged()
    }
    fun addData(data: ArrayList<ImgItems>){
        data?.let {
            imgItems.addAll(it)
        }
//        imgItems.addAll(data)
        notifyDataSetChanged()
    }


    fun setItemClickListener(imgItemRecyclerListener: ImgItemRecyclerListener) {
        this.imgItemRecyclerListener = imgItemRecyclerListener
    }

}