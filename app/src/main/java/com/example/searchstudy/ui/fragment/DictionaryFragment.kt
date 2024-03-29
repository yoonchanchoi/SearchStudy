package com.example.searchstudy.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.databinding.FragmentDictionaryBinding
import com.example.searchstudy.network.models.response.AllItem
import com.example.searchstudy.network.models.response.ResultSearchAll
import com.example.searchstudy.ui.activity.WebViewActivity
import com.example.searchstudy.ui.dialog.LoadingProgressDialog
import com.example.searchstudy.ui.recyclerview.dictionary.DictionaryAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import com.example.searchstudy.util.Util
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()

    private lateinit var binding: FragmentDictionaryBinding

    private lateinit var dictionaryAdapter: DictionaryAdapter

    private lateinit var progressBar: LoadingProgressDialog

    private var resDicTotalCount = 0  //백과사전 api의 총 반환 갯수

//    private lateinit var dicItems : ArrayList<AllItem>

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

    private fun init() {
        settingAdapter()
        initObserve()
        initListener()


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

    /**
     * 옵저버 세팅
     */
    private fun initObserve() {
        viewModel.dictionaryResultSearchArraylist.observe(viewLifecycleOwner) { it ->
            resDicTotalCount = it.total
//            it.allItems.map { allItem -> allItem.type = Constants.ITEMS }
            Util.dataSort(it.allItems)

            if (!viewModel.dicMoreLoad) {
//                dicItems=it.allItems
                dictionaryAdapter.setData(it.allItems)
            } else {
//                dicItems.addAll(it.allItems)
                dictionaryAdapter.addData(it.allItems)
            }
            progressBar.dismiss()
        }
    }


    /**
     * 리스너 세팅
     */
    private fun initListener(){

        //백과사전 아이템 클릭 리스너
        dictionaryAdapter.setItemClickListener(object : DictionaryAdapter.DicItemRecyclerListener {
            override fun onItemClick(link: String) {
                val intent = Intent(requireContext(), WebViewActivity::class.java)
                intent.putExtra(
                    Constants.DITAIL_WEB_LOAD_URL,
                    link
                )
                startActivity(intent)
            }
        })

        //스크롤 리스너
        progressBar = LoadingProgressDialog(requireContext())
        binding.rvDictionary.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() + 1
                val itemTotalCount = recyclerView.adapter?.itemCount ?: 0 // 어댑터에 등록된 아이템의 총 개수 -1

                if (resDicTotalCount > itemTotalCount) {
                    if (!binding.rvDictionary.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                        viewModel.requestDictionary(viewModel.query, itemTotalCount + 1, true)
//                        viewModel.lastDicItemPoint = itemTotalCount + 1
                        progressBar.show()
                    }
                }
            }
        })
    }
}