package com.example.searchstudy.ui.recyclerview.all.child


import android.content.Context
import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.R
import com.example.searchstudy.databinding.AllRecyclerviewItemViewBinding
import com.example.searchstudy.network.models.response.AllItem


class AllChildItemViewHolder(private val context: Context, private val binding: AllRecyclerviewItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    //    fun bind(allItems: AllItem, allItemRecyclerListener: AllItemAdapter.AllItemRecyclerListener) {
//    fun bind(allItems: AllItem, allChildRecyclerListener: AllChildRecyclerListener) {
    fun bind(allItems: AllItem, allChildRecyclerListener: AllChildRecyclerListener) {
//        listener: (link: String) -> Unit
        binding.tvTitle.text = Html.fromHtml(allItems.title, Html.FROM_HTML_MODE_LEGACY)
        if (allItems.postdate.isEmpty()) {
            binding.tvCatagory.text = context.getString(R.string.catagory_cafa)
            binding.tvName.text = allItems.cafename
        } else {
            binding.tvName.text = allItems.bloggername
            binding.tvCatagory.text = context.getString(R.string.catagory_blog)
        }
        binding.tvDescription.text = Html.fromHtml(allItems.description, Html.FROM_HTML_MODE_LEGACY)
//    binding.clAllView.setOnClickListener { allChildRecyclerListener.onItemClick(Constants.VIEW,bindingAdapterPosition)}
//        binding.clAllView.setOnClickListener { listener(allItems.link) }

    binding.clAllView.setOnClickListener { allChildRecyclerListener.onItemClick(allItems.link)}
    }

}
