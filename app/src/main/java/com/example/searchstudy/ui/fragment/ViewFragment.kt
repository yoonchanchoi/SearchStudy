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
import com.example.searchstudy.databinding.FragmentImgBinding
import com.example.searchstudy.databinding.FragmentViewBinding
import com.example.searchstudy.network.models.response.DictionaryItems
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.recyclerview.img.ImgAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentViewBinding
//    private lateinit var viewItems: ArrayList<ViewItems>
//    private lateinit var viewItemsAdapter: DictionaryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
     * 백과사전 데이터 세팅
     */
    private fun init() {
//        viewModel.imgItemsArraylist.observe(viewLifecycleOwner){
//            viewItems=it
//        }
//        settingAdapter()
    }

    /**
     * 어댑터 세팅
     */
//    private fun settingAdapter() {
//        Log.e("cyc","fragment_dictionaryItems-->${imgItems}")
//        imgAdapter = ImgAdapter(imgItems)
//        val searchLinearLayoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
//        searchLinearLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
//        binding.rvView.apply {
//            layoutManager = searchLinearLayoutManager
//            adapter = Adapter
//        }
//    }
}