//package com.example.searchstudy.ui.recyclerview.all
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.searchstudy.databinding.AllRecyclerviewItemDictionaryBinding
//import com.example.searchstudy.databinding.AllRecyclerviewItemViewBinding
//import com.example.searchstudy.network.models.dto.integrated.Integrated
//
//class AllInAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    private var arrayIntegratedData = mutableListOf<Integrated>()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//
//        return when(viewType){
//            1->{
//                val allRecyclerviewItemViewBinding = AllRecyclerviewItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//                return AllAdapterViewItemViewHolder(allRecyclerviewItemViewBinding)
//            }
//            else ->{
//                val allRecyclerviewItemDictionaryBinding = AllRecyclerviewItemDictionaryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//                return AllAdapterDictionaryItemViewHolder(allRecyclerviewItemDictionaryBinding)
//            }
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//        when(arrayIntegratedData[position].type){
//            1->{
//                (holder as AllAdapterViewItemViewHolder).bind(arrayIntegratedData[position])
//                holder.setIsRecyclable(false)
//            }
//            else->{
//                (holder as AllAdapterDictionaryItemViewHolder).bind(arrayIntegratedData[position])
//                holder.setIsRecyclable(false)
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return arrayIntegratedData.size
//    }
//
//    fun setData(arrayIntegrated: ArrayList<Integrated>) {
//        Log.e("cyc", "ViewAdapter----data---->data")
//        arrayIntegratedData = arrayIntegrated
//        notifyDataSetChanged()
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return arrayIntegratedData[position].type
//    }
//}