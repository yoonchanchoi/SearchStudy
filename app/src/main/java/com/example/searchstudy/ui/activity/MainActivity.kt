package com.example.searchstudy.ui.activity

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudy.databinding.ActivityMainBinding
import com.example.searchstudy.network.models.dto.searchDto.SearchData
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


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchRecyclerListener {

    @Inject
    lateinit var pref: Pref

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var searchDataList: ArrayList<SearchData>
    private lateinit var searchAdapter: SearchAdapter
    private var mainBaseheight = 0
    private var count =0



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
        viewPagerSetting()
        searchDataList = pref.getSearchList() as ArrayList<SearchData>
        searchAdapterSetting(searchDataList)
        Log.e("cyc", "searchDataList.size-->${searchDataList.size}")
        Log.e("cyc", "searchAdapter.itemCount()-->${searchAdapter.itemCount}")
        checkSearchData()
    }

    /**
     * 리스너
     */
    private fun initListener() {
        //서치뷰 리스너
        binding.etSearch.setOnFocusChangeListener{ v, focus ->
                when (focus) {
                    true -> {
                        binding.clSearch.visibility = View.VISIBLE
                        binding.clSearch.bringToFront()
                        binding.clMain.setOnTouchListener { view, event ->
//                            binding.svSearch.clearFocus()
                            hideKeyboard()
                            false
                        }
                    }
                    false -> {
                        binding.clSearch.visibility = View.INVISIBLE
//                        binding.clSearchResult.visibility = View.VISIBLE
                    }
                }
            }

//            this.setOnCloseListener { false }

        binding.btnSearchBack.setOnClickListener {
            binding.etSearch.clearFocus()
            hideKeyboard()
        }
        binding.btnSearchText.setOnClickListener {
            val text=binding.etSearch.text.toString()
            Log.e("cyc","text->${text}")
            saveSearchData(text)
            searchAdapter.notifyDataSetChanged()
            hideKeyboard()
            viewModel.searchQury(text)
            checkSearchData()
            binding.etSearch.clearFocus()
        }

        binding.tvDeleteAll.setOnClickListener {
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
        if (searchAdapter.itemCount > 0) {
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

//            this.mySearchHistoryRecyclerViewAdapter.notifDataSetChanged()
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

    /**
     * 뷰페이저 세팅
     */
    private fun viewPagerSetting() {
        val tabTitle = listOf<String>("통합", "VIEW", "백과사전", "이미지")
        binding.vp2.adapter = ViewpagerFragmentAdapter(this)
        TabLayoutMediator(binding.tlMenu, binding.vp2) { tab, postion ->
            tab.text = tabTitle[postion]
        }.attach()
    }

    private fun hideKeyboard() {
        val inputManager: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.etSearch.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        binding.etSearch.clearFocus()
    }


//    // 화면 클릭하여 키보드 숨기기 및 포커스 제거
//    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
//        if (event?.action === MotionEvent.ACTION_DOWN) {
//            val v = currentFocus
//            if (v is SearchView) {
//                val outRect = Rect()
//                v.getGlobalVisibleRect(outRect)
//                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
//                    v.clearFocus()
//                    val imm: InputMethodManager =
//                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
//                }
//            }
//        }
//        return super.dispatchTouchEvent(event)
//    }


}







//                        binding.clMain.viewTreeObserver.addOnGlobalLayoutListener {
//                            Log.e("cyc","count->${count}")
//                            val displayRect =
//                                Rect().apply { binding.clMain.getWindowVisibleDisplayFrame(this) }
//                            val min = displayRect.bottom
//                            if (min == mainBaseheight) {
//                                if(count==0){
//                                }else{
//                                    binding.svSearch.clearFocus()
//                                }
//                            }
//                        }


//    private fun baseheightSetting() {
//        val displayRect = Rect().apply { binding.clMain.getWindowVisibleDisplayFrame(this) }
//        val baseheight = displayRect.bottom
//        Log.e("cyc", "여기여기displayRect.bottom-->${displayRect.bottom}")
//        if (baseheight > 0) {
//            Log.e("cyc", "baseheight>0")
//            mainBaseheight = baseheight
//        }
//    }




////서치뷰 리스너
//binding.svSearch.apply {
//    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//        override fun onQueryTextSubmit(searchText: String?): Boolean {
//            searchText?.let {
//                saveSearchData(it)
//                searchAdapter.notifyDataSetChanged()
//                viewModel.searchQury(searchText)
//                checkSearchData()
//                binding.svSearch.clearFocus()
//            }
//            return true
//        }
//
//
//        override fun onQueryTextChange(searchText: String?): Boolean {
//
//            return true
//        }
//    })
//
//    this.setOnQueryTextFocusChangeListener { v, focus ->
//        when (focus) {
//            true -> {
//                binding.clSearch.visibility = View.VISIBLE
//                binding.clSearch.bringToFront()
//                binding.clMain.setOnTouchListener { view, event ->
////                            binding.svSearch.clearFocus()
//                    hideKeyboard()
//                    false
//                }
//            }
//            false -> {
//                binding.clSearch.visibility = View.INVISIBLE
////                        binding.clSearchResult.visibility = View.VISIBLE
//            }
//        }
//    }
//
////            this.setOnCloseListener { false }
//
//}