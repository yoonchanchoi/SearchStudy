package com.example.searchstudy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.databinding.FragmentAllBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.network.models.response.AllItems
import com.example.searchstudy.ui.recyclerview.all.AllAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentAllBinding
    private var allAdapter = AllAdapter()
    private val tempAllViewItems = ArrayList<AllItems>()
    private val tempAllIntegratedArrayList = ArrayList<Integrated>()


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

    /**
     * 옵저버 세팅
     */
    private fun init() {
        adapterSetting()

        viewModel.blogItemsArraylist.observe(viewLifecycleOwner) {
            tempAllViewItems.clear()
            tempAllIntegratedArrayList.clear()
            tempAllViewItems.addAll(it)
        }
        viewModel.cafeItemsArraylist.observe(viewLifecycleOwner) {
            tempAllViewItems.addAll(it)
            Util.dataSort(tempAllViewItems)
            tempAllIntegratedArrayList.add(
                Integrated(
                    "VIEW",
                    Util.dataExtraction(tempAllViewItems),
                    2
                )
            )
        }
        viewModel.dictionaryItemsArraylist.observe(viewLifecycleOwner) {
            tempAllIntegratedArrayList.add(Integrated("백과 사전", Util.dataExtraction(it), 2))
            viewModel.setAllIntegratedArraylist(tempAllIntegratedArrayList)
        }

        viewModel.allIntegratedArraylist.observe(viewLifecycleOwner) {
            allAdapter.setData(it)
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