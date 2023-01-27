package com.example.searchstudy.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.databinding.ActivityMainBinding
import com.example.searchstudy.network.models.dto.search.SearchData
import com.example.searchstudy.ui.recyclerview.SearchAdapter
import com.example.searchstudy.ui.recyclerview.SearchRecyclerListener
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
import com.example.searchstudy.util.Pref
import com.example.searchstudy.util.toDateString
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchRecyclerListener {

    @Inject
    lateinit var pref: Pref

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var searchDataList: ArrayList<SearchData>
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initData()
        initListener()
    }

    private fun initData() {
        searchDataList = pref.getSearchList() as ArrayList<SearchData>
        searchAdapterSetting(searchDataList)

    }

    private fun initListener() {

        binding.svSearch.apply {
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(searchText: String?): Boolean {
                    searchText?.let {
                        saveSearchData(it)
                    }
                    return true
                }

                override fun onQueryTextChange(searchText: String?): Boolean {

                    return true
                }

            })

            this.setOnQueryTextFocusChangeListener { _, focus ->
                when (focus) {
                    true -> {
                        binding.clSearch.visibility = View.VISIBLE
                    }
                    false -> {
                        binding.clSearch.visibility = View.INVISIBLE
                        checkSearchData()
                    }
                }
            }
        }
        binding.tvDeleteAll.apply {

        }

    }

    private fun checkSearchData() {
        if (searchDataList.size>0) {
            binding.tvSearchEmpty.visibility = View.VISIBLE
        } else {
            binding.tvSearchEmpty.visibility = View.INVISIBLE
        }
    }

    private fun saveSearchData(searchTerm: String) {
        var indexListToRemove = ArrayList<Int>()

        this.searchDataList.forEachIndexed { index, searchDataItem ->

            if (searchDataItem.searchText == searchTerm) {
                indexListToRemove.add(index)
            }
        }

        indexListToRemove.forEach {
            this.searchDataList.removeAt(it)
        }

        // 새 아이템 넣기
        val newSearchData = SearchData(searchText = searchTerm, searchTime = Date().toDateString())
        this.searchDataList.add(newSearchData)

        // 기존 데이터에 덮어쓰기
        pref.saveSearchList(this.searchDataList)

//            this.mySearchHistoryRecyclerViewAdapter.notifyDataSetChanged()
    }

    private fun searchAdapterSetting(searchDataList: ArrayList<SearchData>) {
        searchAdapter = SearchAdapter(this, searchDataList)
        val searchLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        searchLinearLayoutManager.stackFromEnd = true // 키보드 열릴시 recycclerview 스크롤 처리
        binding.rvSearch.apply {
            layoutManager = searchLinearLayoutManager
//            this.scrollToPosition(searchAdapter.itemCount - 1) // 해당 포지션으로 스크롤 이동
            adapter = searchAdapter
        }
    }

    override fun onItemDelete(position: Int) {
        searchDataList.removeAt(position)
        pref.saveSearchList(searchDataList)
        searchAdapter.notifyDataSetChanged()
//        checkSearchData()
    }

    override fun onItemClick(position: Int) {
    }


}


