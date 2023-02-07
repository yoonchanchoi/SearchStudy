package com.example.searchstudy.ui.recyclerview.all

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.AllRecyclerviewItemOutBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter

class OutAllAdapterViewHolder(private val binding: AllRecyclerviewItemOutBinding) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(integrated: Integrated){
        if(integrated.title=="VIEW"){
            binding.tvCatagory.text=integrated.title
            binding.rvIn.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = ViewAdapter().apply {
                    setData(integrated.arraylist)
                }

            }
        }else{
            binding.tvCatagory.text=integrated.title
            binding.rvIn.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = DictionaryAdapter().apply {
                    setData(integrated.arraylist)
                }

            }
        }
    }










//    fun bind(allItems: AllItems) {
//        if(allItems.bloggername.isEmpty()){
//            if(allItems.cafename.isEmpty()){
//                dicVisible()
//                viewInvisible()
//                binding.tvDicTitle.text=Html.fromHtml(allItems.title)
//                binding.tvDicLink.text=allItems.link
//                binding.tvDicCatagory.text="백과 사전"
//            }else{
//                viewVisible()
//                dicInvisible()
//                binding.tvTitle.text=Html.fromHtml(allItems.title)
//                binding.tvName.text = allItems.cafename
//                binding.tvCatagory.text="카페"
//            }
//        }else{
//            viewVisible()
//            dicInvisible()
//            binding.tvTitle.text=Html.fromHtml(allItems.title)
//            binding.tvName.text=allItems.bloggername
//            binding.tvCatagory.text="블로그"
//        }
//        binding.tvDescription.text=Html.fromHtml(allItems.description)
//    }
//
//    private fun viewInvisible(){
//        binding.tvTitle.visibility= View.INVISIBLE
//        binding.tvName.visibility= View.INVISIBLE
//        binding.tvCatagory.visibility= View.INVISIBLE
//    }
//    private fun dicInvisible(){
//        binding.tvDicLink.visibility= View.INVISIBLE
//        binding.tvDicTitle.visibility= View.INVISIBLE
//        binding.tvDicCatagory.visibility= View.INVISIBLE
//    }
//    private fun viewVisible(){
//        binding.tvTitle.visibility= View.VISIBLE
//        binding.tvName.visibility= View.VISIBLE
//        binding.tvCatagory.visibility= View.VISIBLE
//    }
//    private fun dicVisible(){
//        binding.tvDicLink.visibility= View.VISIBLE
//        binding.tvDicTitle.visibility= View.VISIBLE
//        binding.tvDicCatagory.visibility= View.VISIBLE
//    }


}
