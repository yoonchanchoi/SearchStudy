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
import com.example.searchstudy.ui.recyclerview.search.SearchAdapter
import com.example.searchstudy.ui.recyclerview.search.SearchRecyclerListener
import com.example.searchstudy.ui.recyclerview.viewpager.ViewpagerFragmentAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Pref
import com.example.searchstudy.util.toDateString
import com.google.android.material.tabs.TabLayoutMediator
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

    /**
     * Data 초기화
     */
    private fun initData() {
        //최근 검색어 데이터 가져와서 어댑터에 세팅
        searchDataList = pref.getSearchList() as ArrayList<SearchData>
        searchAdapterSetting(searchDataList)
        Log.e("cyc", "searchDataList.size-->${searchDataList.size}")
        Log.e("cyc", "searchAdapter.itemCount()-->${searchAdapter.itemCount}")
        checkSearchData()
        viewPagerSetting()

    }

    /**
     * 리스너
     */
    private fun initListener() {
        //서치뷰 리스너
        binding.svSearch.apply {
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(searchText: String?): Boolean {
                    searchText?.let {
                        saveSearchData(it)
                        searchAdapter.notifyDataSetChanged()
                        viewModel.searchQury(searchText)
                        checkSearchData()
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
//                        binding.clSearchResult.visibility = View.INVISIBLE
                    }
                    false -> {
                        binding.clSearch.visibility = View.INVISIBLE
//                        binding.clSearchResult.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.tvDeleteAll.setOnClickListener {
            Log.e("cyc","")
            searchDataList.clear()
            pref.clear()
            searchAdapter.notifyDataSetChanged()
            checkSearchData()
        }

    }
    /**
     * 최근 검색어에 데이터의 유무에 따른 뷰 보여주기
     */
    private fun checkSearchData() {
        Log.e("cyc","checkSearchData")
        if (searchAdapter.itemCount>0) {
            binding.tvSearchEmpty.visibility = View.INVISIBLE
        } else {
            binding.tvSearchEmpty.visibility = View.VISIBLE
        }
    }
    /**
     * 최근 검색어에 저장
     */
    private fun saveSearchData(searchTerm: String) {
        val indexListToRemove = ArrayList<Int>()

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

    /**
     * 최근 검색어 어댑터 세팅
     */
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

    /**
     * 아이템 삭제
     */
    override fun onItemDelete(position: Int) {
        Log.e("cyc","onItemDelete")
        searchDataList.removeAt(position)
        pref.saveSearchList(searchDataList)
        searchAdapter.notifyDataSetChanged()
        checkSearchData()
    }


    /**
     * 아이템 클릭
     */
    override fun onItemClick(position: Int) {

    }

   private fun viewPagerSetting(){
       val tabTitle = listOf<String>("통합","VIEW", "백과사전","이미지")
       binding.vp2.adapter = ViewpagerFragmentAdapter(this)
       TabLayoutMediator(binding.tlMenu, binding.vp2){ tab,postion->
           tab.text = tabTitle[postion]
       }.attach()
   }


}


