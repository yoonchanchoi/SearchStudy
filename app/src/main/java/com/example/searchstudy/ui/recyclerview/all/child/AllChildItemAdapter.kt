package com.example.searchstudy.ui.recyclerview.all.child

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemDictionaryBinding
import com.example.searchstudy.databinding.AllRecyclerviewItemViewBinding
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.util.Constants

//class AllChildItemAdapter(private val context: Context,private val allChildRecyclerListener: AllChildRecyclerListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
class AllChildItemAdapter(
    private val context: Context,
    private val listener: (link: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    interface AllItemRecyclerListener{
//        fun onItemClick(position: Int)
//    }
//private val allChildRecyclerListener: (link: String) -> Unit

    private val allItems = mutableListOf<AllItem>()

//    private lateinit var allItemRecyclerListener: AllItemRecyclerListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //dictionary의 타입에 따른 통합, 백과사전 뷰홀더 바인딩 구성
        return when (viewType) {
            Constants.VIEW -> {
                val allRecyclerviewItemBinding = AllRecyclerviewItemViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AllChildItemViewHolder(context, allRecyclerviewItemBinding)
                // AllItemViewHolder
                // -> AllChildItemViewHolder
            }
            else -> {
                val allRecyclerviewItemDictionaryBinding =
                    AllRecyclerviewItemDictionaryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                AllChildItemDicViewHolder(allRecyclerviewItemDictionaryBinding)
                // AllChildItemDicViewHolder
            }
        }
    }

    // for (0.. 10) {
    // data[pos]
    // listener.onItem(data[pos])
    // }
    // 스크롤을 해도 계속 존재
    // 아이템뷰를 itemCount만큼 계속 가지고 있음 ex) count = 100이면 아이템뷰도 100개 생성
    // 어댑터는 itemCount에 상관없이 화면에 보여줄수 있는 갯수만 생성해서 아이템뷰는 돌려쓰고, 데이터만 포지션에 따라 세팅
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //dictionary의 타입에 따른 통합, 백과사전 뷰홀더 구성

        when (allItems[position].type) {
            Constants.VIEW -> {
//                (holder as AllChildItemViewHolder).bind(allItems[position], allChildRecyclerListener)
                (holder as AllChildItemViewHolder).bind(allItems[position], listener)

                holder.setIsRecyclable(false)
            }
            else -> {
//                (holder as AllChildItemDicViewHolder).bind(allItems[position], allChildRecyclerListener)
                (holder as AllChildItemDicViewHolder).bind(allItems[position], listener)
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int {
        return allItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return allItems[position].type
    }


    fun setData(data: ArrayList<AllItem>) {
        data?.let {
            allItems.addAll(it)
        }
        notifyDataSetChanged()
    }
}