package com.example.searchstudy.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.searchstudy.databinding.FragmentViewBinding
import com.example.searchstudy.network.models.response.ViewItems
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.recyclerview.view.ViewAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentViewBinding
    private var viewAdapter= ViewAdapter()
    private lateinit var viewItems: ArrayList<ViewItems>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    /**
     * view 데이터 세팅
     */
    private fun init() {
        viewAdapterSetting()

        viewModel.cafeItemsArraylist.observe(viewLifecycleOwner){
//            viewAdapter.setData(it)
            viewItems.addAll(it)


        }
        viewModel.blogItemsArraylist.observe(viewLifecycleOwner){
//            viewAdapter.setData(it)
            viewItems.addAll(it)


        }
//        viewItems?.let {
//            viewAdapter.setData(it)
//        }


    }

    /**
     * 어댑터 세팅
     */
    private fun viewAdapterSetting() {
        viewAdapter = ViewAdapter()
        viewAdapter.removeAll()
        val rvViewLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.rvView.apply {
            layoutManager=rvViewLayoutManager
            adapter=viewAdapter
        }
    }
}