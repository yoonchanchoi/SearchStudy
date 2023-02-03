//package com.example.searchstudy.ui.recyclerview.img
//
//import android.text.Html
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.searchstudy.R
//import com.example.searchstudy.databinding.CafeRecyclerviewItemBinding
//import com.example.searchstudy.databinding.ImgRecyclerviewItemBinding
//import com.example.searchstudy.network.models.response.BlogItems
//import com.example.searchstudy.network.models.response.CafeItems
//import com.example.searchstudy.network.models.response.ImgItems
//
//class CafeViewHolder(private val binding: CafeRecyclerviewItemBinding) :
//    RecyclerView.ViewHolder(binding.root) {
//
//    fun bind(cafeItems: CafeItems) {
//        binding.tvName.text = cafeItems.cafename
//        binding.tvTitle.text = Html.fromHtml(cafeItems.title)
//        binding.tvDescription.text = Html.fromHtml(cafeItems.description)
//
//    }
//}