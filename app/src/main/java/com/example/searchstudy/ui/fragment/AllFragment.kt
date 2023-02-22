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
//            Log.e("cyc","")
//            Log.e("cyc","-----------------------------------------------------------------")
//            Log.e("cyc","blog--it.allItems--->${it.allItems}")
//            Log.e("cyc","-----------------------------------------------------------------")
//            Log.e("cyc","")
            if (!viewModel.blogMoreLoad) {
//                Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
//                Log.e("cyc","blog--it.allItems--->${it.allItems}")
//                Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
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
                Log.e("cyc","!viewModel.cafeMoreLoad--->${!viewModel.cafeMoreLoad}")
                if (!viewModel.cafeMoreLoad) {
//                    Log.e("cyc","========================변경===전========================")
//
//                    Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
//                    Log.e("cyc","cafe--it.allItems--->${it.allItems}")
//                    Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
                    it.allItems.map { allItems ->
                        allItems.type = Constants.ALLITEMS
                    }
//                    Log.e("cyc","========================변경===후========================")
//
//                    Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
//                    Log.e("cyc","cafe--it.allItems--->${it.allItems}")
//                    Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
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
//                    Log.e("cyc","")
//                    Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
//                    Log.e("cyc","dictionaryResultSearchArraylist")
//                    Log.e("cyc","dic--it.allItems--->${it.allItems}")
//                    Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
                    Log.e("cyc","")
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

//            if(it.allItems.size>0){
//                if (!viewModel.dicMoreLoad) {
//                    Log.e("cyc","")
//                    Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
//                    Log.e("cyc","dictionaryResultSearchArraylist")
//                    Log.e("cyc","dic--it.allItems--->${it.allItems}")
//                    Log.e("cyc","---------------------------------------------------------------------------------------------------------------")
//                    Log.e("cyc","")
//                    it.allItems.map { allItems ->
//                        allItems.type = Constants.ALLITEMS
//                    }
//                    Util.dataSort(it.allItems)
//                    tempResultSearchAll.add(
//                        ResultSearchAll(
//                            category = "백과사전",
//                            allItems = Util.dataExtraction(it.allItems)
//                        )
//                    )
//                }
//            }
//            allAdapter.setData(tempResultSearchAll)

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
}