package com.example.searchstudy.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.databinding.FragmentAllBinding
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.network.models.response.ResultSearchAll
import com.example.searchstudy.ui.recyclerview.all.AllAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import com.example.searchstudy.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private var allAdapter = AllAdapter()
    private val tempResultSearchAll = ArrayList<ResultSearchAll>()
    private val tempAllViewItems = ArrayList<AllItem>()
    private lateinit var binding: FragmentAllBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("cyc","")
        Log.e("cyc","onCreateView")
        Log.e("cyc","")
        // Inflate the layout for this fragment
        binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        adapterSetting()
        initObserve()
    }

    /**
     * 옵저버 세팅
     */
    private fun initObserve() {
        viewModel.blogResultSearchArraylist.observe(viewLifecycleOwner) {

            if (!viewModel.blogMoreLoad) {

                tempAllViewItems.clear()
                tempResultSearchAll.clear()
                it.allItems.map { allItems ->
                    allItems.type = Constants.ALLITEMS
                }
                tempAllViewItems.addAll(it.allItems)
            }
        }
        viewModel.cafeResultSearchArraylist.observe(viewLifecycleOwner) {

            if(it.allItems.size>0){
                if (!viewModel.cafeMoreLoad) {

                    it.allItems.map { allItems ->
                        allItems.type = Constants.ALLITEMS
                    }

                    tempAllViewItems.addAll(it.allItems)
                    Util.dataSort(tempAllViewItems)
                    tempResultSearchAll.add(
                        ResultSearchAll(
                            category = "VIEW",
                            allItems = Util.dataExtraction(tempAllViewItems)
                        )
                    )
                }
            }
        }
        viewModel.dictionaryResultSearchArraylist.observe(viewLifecycleOwner) {

                if (!viewModel.dicMoreLoad) {

                    it.allItems.map { allItems ->
                        allItems.type = Constants.ALLITEMS
                    }
                    if(it.allItems.size>0) {
                        Util.dataSort(it.allItems)
                        tempResultSearchAll.add(
                            ResultSearchAll(
                                category = "백과사전",
                                allItems = Util.dataExtraction(it.allItems)
                            )
                        )
                    }

                    allAdapter.setData(tempResultSearchAll)
                }
        }
    }

    /**
     * 어댑터 세팅
     */
    private fun adapterSetting() {
        val allLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvOutAll.apply {
            layoutManager = allLayoutManager
            adapter = allAdapter
        }
    }

//    override fun onResume() {
////        Log.e("cyc","")
////        Log.e("cyc","onResume")
////        Log.e("cyc","")
////        for(i in tempResultSearchAll){
////            tempResultSearchAll.map {
////                it.allItems.map {
////                    it.type=Constants.ALLITEMS
////                }
////            }
////        }
//        for(i in tempResultSearchAll.indices){
//            Log.e("cyc","tempResultSearchAll--->${tempResultSearchAll[i]}")
//        }
//        allAdapter.setData(tempResultSearchAll)
//
//
//
//        super.onResume()
//
//    }
}