package com.example.searchstudy.ui.recyclerview.all

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.R
import com.example.searchstudy.databinding.AllRecyclerviewItemBinding
import com.example.searchstudy.databinding.AllTranslationRecyclerviewItemBinding
//import com.example.searchstudy.network.models.dto.searchDto.Papago.PapagoResult
import com.example.searchstudy.network.models.response.ResultPapago
import com.example.searchstudy.network.models.response.ResultSearchAll
import com.example.searchstudy.ui.recyclerview.all.child.AllChildItemAdapter
import com.example.searchstudy.util.toLanguageString

class AllTranslationViewHolder(private val binding: AllTranslationRecyclerviewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {



    fun bind(resultPapago: ResultPapago) {
        binding.tvTitle.text = "Papago"
        binding.tvSourceLanguage.text = resultPapago.srcLangType.toLanguageString()
        binding.tvTargetLanguage.text = resultPapago.tarLangType.toLanguageString()
        binding.tvOriginal.text = resultPapago.originText
        binding.tvTranslation.text = resultPapago.translatedText
//        binding.tvSourceLanguage.text = resultPapago.originText
//        binding.tvTargetLanguage.text = resultPapago.translatedText
//        binding.tvOriginal.text = resultPapago.srcLangType
//        binding.tvTranslation.text = resultPapago.tarLangType
    }

}