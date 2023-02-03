//package com.example.searchstudy.ui.recyclerview.img
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.searchstudy.databinding.CafeRecyclerviewItemBinding
//import com.example.searchstudy.databinding.ImgRecyclerviewItemBinding
//import com.example.searchstudy.network.models.response.CafeItems
//import com.example.searchstudy.network.models.response.ImgItems
//
//class CafeAdapter() : RecyclerView.Adapter<CafeViewHolder>() {
//
//
//    var cafeItems = mutableListOf<CafeItems>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
//
//        val itemBinding =
//            CafeRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return CafeViewHolder(itemBinding)
//    }
//
//    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
//        holder.bind(cafeItems[position])
////        if (cafeItems[position].type == "BLOG") {
////
////        } else {
////
////        }
//    }
//
//    override fun getItemCount(): Int {
//        return cafeItems.size
//    }
//
//    fun setData(data: ArrayList<CafeItems>) {
//        cafeItems = data
//        notifyDataSetChanged()
//    }
//
//}