package com.example.searchstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.R
import com.example.searchstudy.databinding.FragmentAllBinding
import com.example.searchstudy.databinding.FragmentViewBinding
import com.example.searchstudy.network.models.response.DictionaryItems
import com.example.searchstudy.network.models.response.ViewItems
import com.example.searchstudy.ui.recyclerview.all.OutAllAdapter
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentAllBinding
    private var outAllAdapter= OutAllAdapter()
    private lateinit var concatAdapter: ConcatAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //여기
        init()
    }


    private fun init(){
        adapterSetting()
        viewModel.cafeItemsArraylist.observe(viewLifecycleOwner){
//            viewModel.addAllData(it)
        }
        viewModel.blogItemsArraylist.observe(viewLifecycleOwner){
//            viewModel.addAllData(it)
        }

        viewModel.dictionaryItemsArraylist.observe(viewLifecycleOwner){
//            viewModel.addAllData(it)
        }

    }


    private fun adapterSetting(){
        outAllAdapter = OutAllAdapter()
        val allLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        allLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvOutAll.apply {
            layoutManager = allLayoutManager
            adapter = outAllAdapter
        }
    }



}