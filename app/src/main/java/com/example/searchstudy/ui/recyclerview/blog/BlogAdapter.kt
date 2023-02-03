//package com.example.searchstudy.ui.recyclerview.img
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.searchstudy.databinding.BlogRecyclerviewItemBinding
//import com.example.searchstudy.databinding.ImgRecyclerviewItemBinding
//import com.example.searchstudy.network.models.response.BlogItems
//import com.example.searchstudy.network.models.response.ImgItems
//
//class BlogAdapter() : RecyclerView.Adapter<BlogViewHolder>() {
//
//
//    var blogItems = mutableListOf<BlogItems>()
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
//        val itemBinding = BlogRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return BlogViewHolder(itemBinding)
//    }
//
//    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
//        holder.bind(blogItems[position])
//    }
//
//    override fun getItemCount(): Int {
//        return blogItems.size
//    }
//
//    fun setData(data: ArrayList<BlogItems>) {
//        blogItems = data
//        notifyDataSetChanged()
//    }
//
//}