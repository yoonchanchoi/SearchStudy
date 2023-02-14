package com.example.searchstudy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.databinding.FragmentDictionaryBinding
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Util
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
        init()
    }

    /**
     * 백과사전 데이터 세팅
     */
    private fun init() {
        settingAdapter()
        initObserve()

    }

    /**
     * 옵저버 세팅
     */
    private fun initObserve() {
        viewModel.dictionaryItemsArraylist.observe(viewLifecycleOwner) {
            Util.dataSort(it)
            val dictionaryIntegrated = (Integrated(allItemsarraylist = it))
            viewModel.setDicIntegrateditems(dictionaryIntegrated)
        }
        viewModel.dictionaryIntegrated.observe(viewLifecycleOwner) {
            dictionaryAdapter.setData(it)
        }
    }

    /**
     * 어댑터 세팅
     */
    private fun settingAdapter() {
        dictionaryAdapter = DictionaryAdapter()
        val dictionaryLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        dictionaryLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvDictionary.apply {
            layoutManager = dictionaryLayoutManager
            adapter = dictionaryAdapter

        }
    }
}