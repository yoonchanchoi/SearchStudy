package com.example.searchstudy.ui.recyclerview.img

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchstudy.databinding.ImgRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.ImgItems

class ImgViewHolder(private val binding: ImgRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imgItems: ImgItems,imgItemRecyclerListener: ImgAdapter.ImgItemRecyclerListener) {
        Glide.with(itemView)
            .load(imgItems.thumbnail)
            .into(binding.ivThumbnail)

        binding.cv.setOnClickListener{imgItemRecyclerListener.onItemClick(imgItems.link)}
    }
}