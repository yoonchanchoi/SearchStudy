package com.example.searchstudy.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.R
import com.example.searchstudy.databinding.FragmentAllBinding
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.network.models.response.ResultSearchAll
import com.example.searchstudy.ui.activity.WebViewActivity
import com.example.searchstudy.ui.recyclerview.all.AllItemsAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import com.example.searchstudy.util.Util
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AllFragment : Fragment() {
//class AllFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()

    private lateinit var binding: FragmentAllBinding

    private val tempResultSearchAll = ArrayList<ResultSearchAll>()// 임시 통합 아이템

    private val tempAllViewItems = ArrayList<AllItem>() // 임시 VEIW 아이템

    private lateinit var allItemsAdapter: AllItemsAdapter

//


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
//        allAdapter.set
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
                    allItems.type = Constants.VIEW
                }
                tempAllViewItems.addAll(it.allItems)
            }
        }
        viewModel.cafeResultSearchArraylist.observe(viewLifecycleOwner) {

            if (it.allItems.size > 0) {
                if (!viewModel.cafeMoreLoad) {

                    it.allItems.map { allItems ->
                        allItems.type = Constants.VIEW
                    }

                    tempAllViewItems.addAll(it.allItems)
                    Util.dataSort(tempAllViewItems)
                    tempResultSearchAll.add(
                        ResultSearchAll(
                            category = getString(R.string.tab_title_view),
                            allItems = Util.dataExtraction(tempAllViewItems)
                        )
                    )
                }
            }
        }
        viewModel.dictionaryResultSearchArraylist.observe(viewLifecycleOwner) {

            if (!viewModel.dicMoreLoad) {

                it.allItems.map { allItems ->
                    allItems.type = Constants.DICTIONARY
                }
                if (it.allItems.size > 0) {
                    Util.dataSort(it.allItems)
                    tempResultSearchAll.add(
                        ResultSearchAll(
                            category = getString(R.string.tab_title_dictionary),
                            allItems = Util.dataExtraction(it.allItems)
                        )
                    )
                }
                allItemsAdapter.setData(tempResultSearchAll)
            }
        }
        viewModel.papagoResult.observe(viewLifecycleOwner){
            it.srcLangType
            it.tarLangType
            it.translatedText
        }
    }

    /**
     * 어댑터 세팅
     */
    private fun adapterSetting() {
//        allItemsAdapter = AllItemsAdapter(requireContext(),allChildRecyclerListener)
        allItemsAdapter = AllItemsAdapter(requireContext()) {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            intent.putExtra(Constants.DITAIL_WEB_LOAD_URL, it)
            startActivity(intent)
        }

        val allLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvOutAll.apply {
            layoutManager = allLayoutManager
            adapter = allItemsAdapter
        }
    }
}











//override fun onItemClick(category: Int, position: Int) {
//        // 클릭했을 때 행동이 무엇인가?
//        // -> WebActivity 띄우기
//        //    -> WebActivity를 띄우기 위해서 필요한 것?
//        //       -> link가 필요함
//        //
//        val intent = Intent(requireContext(),WebViewActivity::class.java)
////        intent.putExtra(Constants.DITAIL_WEB_LOAD_URL,tempResultSearchAll[])
//    }




//private var allChildRecyclerListener = object : AllChildRecyclerListener {
//        override fun onItemClick(link: String) {
//            val intent = Intent(requireContext(), WebViewActivity::class.java)
//            intent.putExtra(Constants.DITAIL_WEB_LOAD_URL, link)
//            startActivity(intent)
//        }
//    }