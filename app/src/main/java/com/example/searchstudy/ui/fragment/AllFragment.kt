package com.example.searchstudy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.databinding.FragmentAllBinding
import com.example.searchstudy.ui.recyclerview.all.AllAdapter
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentAllBinding
    private var allAdapter= AllAdapter()
    private lateinit var concatAdapter: ConcatAdapter
    private val viewAdapter = ViewAdapter()
    private val dictionaryAdapter= DictionaryAdapter()

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
        init()
    }

    private fun init(){
        adapterSetting()

        viewModel.allIntegratedArraylist.observe(viewLifecycleOwner){
            allAdapter.setData(it)
        }

//        viewModel.viewItemsArrayList.observe(viewLifecycleOwner){
//            viewAdapter.setData(it)
//        }
//        viewModel.dictionaryItemsArraylist.observe(viewLifecycleOwner){
//            dictionaryAdapter.setData(it)
//        }
//        viewModel.allItemsArraylist.observe(viewLifecycleOwner){
//            outAllAdapter.setData(it)
//        }
    }


    private fun adapterSetting(){

        val allLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        binding.rvOutAll.apply {
            layoutManager=allLayoutManager
            adapter=allAdapter
//            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))

        }
//        concatAdapter = ConcatAdapter(viewAdapter, dictionaryAdapter)
//        val allLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
////        allLayoutManager.stackFromEnd = true
//        binding.rvOutAll.apply {
//            layoutManager = allLayoutManager
//            adapter = concatAdapter
//        }


//        outAllAdapter = OutAllAdapter()
//        val allLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        allLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
//        binding.rvOutAll.apply {
//            layoutManager = allLayoutManager
//            adapter = outAllAdapter
//        }
    }
}