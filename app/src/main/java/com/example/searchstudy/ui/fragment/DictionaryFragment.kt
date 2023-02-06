package com.example.searchstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.R
import com.example.searchstudy.databinding.FragmentDictionaryBinding
import com.example.searchstudy.network.models.response.DictionaryItems
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.recyclerview.search.SearchAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentDictionaryBinding
    private lateinit var dictionaryAdapter: DictionaryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    //여기
    //        init()
    }


    /**
     * 백과사전 데이터 세팅
     */
    private fun init() {
        settingAdapter()
        Log.e("cyc", "dicfragment------viewModel.dictionaryItemData--->${viewModel.dictionaryItemsArraylist}")
        viewModel.dictionaryItemsArraylist.observe(viewLifecycleOwner){
        //여기
        //            dictionaryAdapter.setData(it)
        }
    }

    /**
     * 어댑터 세팅
     */
    private fun settingAdapter() {
        dictionaryAdapter = DictionaryAdapter()
        val dictionaryLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        dictionaryLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvDictionary.apply {
            layoutManager = dictionaryLayoutManager
            adapter = dictionaryAdapter
        }
    }
}