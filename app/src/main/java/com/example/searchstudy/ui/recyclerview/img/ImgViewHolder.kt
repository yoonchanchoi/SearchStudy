package com.example.searchstudy.ui.recyclerview.img

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchstudy.R
import com.example.searchstudy.databinding.ImgRecyclerviewItemBinding
import com.example.searchstudy.network.models.response.ImgItems

class ImgViewHolder(private val binding: ImgRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imgItems: ImgItems) {
        Glide.with(itemView)
            .load(imgItems.thumbnail)
            .error(R.drawable.noimag)
            .into(binding.ivThumbnail)
//        binding.ivThumbnail = imgItems.thumbnail
    }
}