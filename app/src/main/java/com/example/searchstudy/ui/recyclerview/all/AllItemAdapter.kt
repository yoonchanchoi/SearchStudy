package com.example.searchstudy.ui.recyclerview.all

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemDictionaryBinding
import com.example.searchstudy.databinding.AllRecyclerviewItemViewBinding
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.util.Constants

class AllItemAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface AllItemRecyclerListener{
        fun onItemClick(position: Int)
    }

    private val AllItems = mutableListOf<AllItem>()
    private lateinit var allItemRecyclerListener: AllItemRecyclerListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //dictionary의 타입에 따른 통합, 백과사전 뷰홀더 바인딩 구성
        return when (viewType) {
            Constants.VIEW -> {
                val allRecyclerviewItemDictionaryBinding = AllRecyclerviewItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AllAdapterItemViewViewHolder(allRecyclerviewItemDictionaryBinding)
                // AllItemViewHolder -> AllChildItemViewHolder
            }
            else -> {
                val allRecyclerviewItemDictionaryBinding =
                    AllRecyclerviewItemDictionaryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                AllAdapterItemDictionaryViewHolder(allRecyclerviewItemDictionaryBinding)
                // AllChildItemDicViewHolder
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //dictionary의 타입에 따른 통합, 백과사전 뷰홀더 구성

        when (AllItems[position].type) {
            Constants.VIEW -> {
                (holder as AllAdapterItemViewViewHolder).bind(AllItems[position], allItemRecyclerListener)
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as AllAdapterItemDictionaryViewHolder).bind(AllItems[position],allItemRecyclerListener)
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int {
        return AllItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return AllItems[position].type
    }


    fun setData(data: ArrayList<AllItem>) {
        data?.let {
            AllItems.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun setItemClickListener(allItemRecyclerListener: AllItemRecyclerListener){
        this.allItemRecyclerListener = allItemRecyclerListener
    }
}