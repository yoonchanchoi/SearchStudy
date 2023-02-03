//package com.example.searchstudy.ui.recyclerview.img
//
//import android.text.Html
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.searchstudy.R
//import com.example.searchstudy.databinding.BlogRecyclerviewItemBinding
//import com.example.searchstudy.databinding.ImgRecyclerviewItemBinding
//import com.example.searchstudy.network.models.response.BlogItems
//import com.example.searchstudy.network.models.response.ImgItems
//
//class BlogViewHolder(private val binding: BlogRecyclerviewItemBinding) :
//    RecyclerView.ViewHolder(binding.root) {
//
//    fun bind(blogItems: BlogItems) {
//        binding.tvName.text = blogItems.bloggername
//        binding.tvTitle.text = Html.fromHtml(blogItems.title)
//        binding.tvDescription.text = Html.fromHtml(blogItems.description)
//    }
//}