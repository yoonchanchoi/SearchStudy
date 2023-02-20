package com.example.searchstudy.ui.activity

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.searchstudy.databinding.ActivityMainBinding
import com.example.searchstudy.databinding.FragmentAdultWarningDialogBinding
import com.example.searchstudy.network.models.dto.searchDto.SearchData
import com.example.searchstudy.ui.dialog.AdultWarningDialogFragment
import com.example.searchstudy.ui.recyclerview.search.SearchAdapter
import com.example.searchstudy.ui.recyclerview.search.SearchRecyclerListener
import com.example.searchstudy.ui.recyclerview.viewpager.ViewpagerFragmentAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.Constants
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
    private lateinit var adapter: ViewpagerFragmentAdapter
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        initData()
        initObserve()
        initListener()
    }

    /**
     * 초기화
     */
    private fun initData() {
        //최근 검색어 데이터 가져와서 어댑터에 세팅
        searchDataList = pref.getSearchList() as ArrayList<SearchData>
        searchAdapterSetting(searchDataList)
        checkSearchTextData()
    }

    /**
     * 리스너
     */
    private fun initListener() {
        //검색 입력 포커스 조절
        binding.etSearch.apply {

            this.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (binding.etSearch.text.toString().isNotEmpty()) {
                        binding.btnTextClear.visibility = View.VISIBLE
                    } else {
                        binding.btnTextClear.visibility = View.INVISIBLE

                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }

            })
            this.setOnEditorActionListener { textView, actionId, keyEvent ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        checkAdultWord()
//                        actionSearch()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }

        //텍스트 전체 삭제 리스너
        binding.btnTextClear.setOnClickListener {
            binding.etSearch.text.clear()
        }
        //검색 입력 포커스 해제
        binding.btnSearchBack.setOnClickListener {
            hideKeyboard()
        }
        //검색 버튼 리스너
        binding.btnSearch.setOnClickListener {
            checkAdultWord()
        }
        //최근 검색어 삭제 리스너
        binding.tvDeleteAll.setOnClickListener {
            searchDataList.clear()
            pref.clear()
            searchAdapter.notifyDataSetChanged()
            checkSearchTextData()
        }
    }

    /**
     * observe
     */
    private fun initObserve() {
        viewModel.blogResultSearchArraylist.observe(this) {
            if (!viewModel.blogMoreLoad) {
                viewModel.requestCafe(query)
            }
        }
        viewModel.cafeResultSearchArraylist.observe(this) {
            if (!viewModel.cafeMoreLoad) {
                viewModel.requestDictionary(query)
            }
        }
        viewModel.checkAdultWord.observe(this) {
            if (it == Constants.MISSWORD) {
                AdultWarningDialogFragment().show(supportFragmentManager, "AdultWarningDialog")
                binding.etSearch.text.clear()
            } else {
                viewModel.requestCheckMissWord(query)
            }
        }
        viewModel.checkMissWord.observe(this){
            if(it.isEmpty()){
                actionSearch()
            }else{
                Toast.makeText(this, "오타를 수정했습니다.", Toast.LENGTH_SHORT).apply {
                    this.setGravity(Gravity.BOTTOM,0,100)
                    this.show()
                }
                Log.e("cyc","오타 수정-->${it}")
                binding.etSearch.setText(it)
                query=it
                actionSearch()
            }
        }
    }

    /**
     * 최근 검색어에 데이터의 유무에 따른 뷰 보여주기
     */
    private fun checkSearchTextData() {
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
        checkSearchTextData()
    }


    /**
     * 아이템 클릭
     */
    override fun onItemClick(position: Int) {
        binding.etSearch.setText(searchDataList.get(position).searchText)
    }

    /**
     * 뷰페이저 세팅
     */
    private fun viewPagerSetting() {
        val tabTitle = listOf<String>("통합", "VIEW", "백과사전", "이미지")
        adapter = ViewpagerFragmentAdapter(this)
        binding.vp2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER  //뷰페이저 오버스크롤 없애기
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tlMenu, binding.vp2) { tab, postion ->
            tab.text = tabTitle[postion]
        }.attach()
    }

    /**
     * 키보드 숨기기
     */
    private fun hideKeyboard() {
        val inputManager: InputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        this.currentFocus?.let {
            inputManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
        binding.etSearch.clearFocus()
    }

    /**
     * 검색 버튼 클리시 활동
     */
    private fun actionSearch() {
        viewPagerSetting()
        hideKeyboard()
        if (!query.isNullOrBlank()) {
            saveSearchData(query)
        }
        viewModel.requestBlog(query = query)
        viewModel.requestImg(query = query)
        viewModel.query = query
        adapter.notifyDataSetChanged()
        binding.clSearch.visibility = View.INVISIBLE
        binding.clSearchResult.visibility = View.VISIBLE
        binding.etSearch.clearFocus()
    }

    private fun checkAdultWord() {
        query = binding.etSearch.text.toString()
        viewModel.requestCheckAdultWord(query)
    }

    /*
    * ui
    *   main
    *     MainActivity
    *     fragment
    *     all
    *     - AllFragment
    *     - viewmodel
    *     - adapter
    *     - holder
    *     view
    *     - ViewFragment
    *     - viewmodel
    *     - adapter
    *     - holder
    *     adapter
    *     viewmodel
    *   setting
    *
    * */
}




