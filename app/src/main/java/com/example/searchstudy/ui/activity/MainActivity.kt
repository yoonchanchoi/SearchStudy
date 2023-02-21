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
import com.example.searchstudy.databinding.ActivityMainBinding
import com.example.searchstudy.network.models.dto.searchDto.SearchData
import com.example.searchstudy.ui.dialog.AdultWarningDialogFragment
import com.example.searchstudy.ui.fragment.AllFragment
import com.example.searchstudy.ui.fragment.DictionaryFragment
import com.example.searchstudy.ui.fragment.ImgFragment
import com.example.searchstudy.ui.fragment.ViewFragment
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

    private var query = ""
    private var dicEndcheck = false
    private var imgEndcehck = false
    private val viewModel: MainActivityViewModel by viewModels()
    private var checkViewpagerAllFragment = false
    private var checkViewpagerViewFragment = false
    private var checkViewpagerDicFragment = false
    private var checkViewpagerImgFragment = false

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchDataList: ArrayList<SearchData>
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var adapter: ViewpagerFragmentAdapter


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
                if (it.allItems.size > 0) {
                    checkViewpagerViewFragment = true
                }
            }
        }
        viewModel.cafeResultSearchArraylist.observe(this) {


            if (!viewModel.cafeMoreLoad) {

                if (it.allItems.size > 0) {
                    checkViewpagerViewFragment = true
                }
                if (checkViewpagerViewFragment) {
                    checkViewpagerAllFragment = true
//                adapter.addFragment("VIEW", ViewFragment())
                }
                viewModel.requestDictionary(query)

            }

        }
        viewModel.dictionaryResultSearchArraylist.observe(this) {
            if(!viewModel.dicMoreLoad){
                if (it.allItems.size > 0) {
                    checkViewpagerAllFragment = true
                    checkViewpagerDicFragment = true
//                adapter.addFragment("백과사전", DictionaryFragment())
                }
                if (checkViewpagerAllFragment) {
//                adapter.firstAddFragment("통합", AllFragment())
                }
                dicEndcheck = true
                if (dicEndcheck && imgEndcehck) {
                    Log.e("cyc", "================================================================")
                    Log.e("cyc", "dic----checkViewpagerAllFragment-->${checkViewpagerAllFragment}")
                    Log.e("cyc", "dic----checkViewpagerViewFragment-->${checkViewpagerViewFragment}")
                    Log.e("cyc", "dic----checkViewpagerDicFragment-->${checkViewpagerDicFragment}")
                    Log.e("cyc", "dic----checkViewpagerImgFragment-->${checkViewpagerImgFragment}")
                    Log.e("cyc", "================================================================")
                    viewPagerSetting()
                    chcekViewpager()
                }
            }

        }
        viewModel.imgResultSearchArraylist.observe(this) {
            if(!viewModel.imgMoreLoad){
                imgEndcehck = true
                if (it.imgItems.size > 0) {
                    checkViewpagerImgFragment = true
//                adapter.lastAddFragment("이미지", ImgFragment())
                }
                if (dicEndcheck && imgEndcehck) {
                    Log.e("cyc", "================================================================")
                    Log.e("cyc", "img----checkViewpagerAllFragment-->${checkViewpagerAllFragment}")
                    Log.e("cyc", "img----checkViewpagerViewFragment-->${checkViewpagerViewFragment}")
                    Log.e("cyc", "img----checkViewpagerDicFragment-->${checkViewpagerDicFragment}")
                    Log.e("cyc", "img----checkViewpagerImgFragment-->${checkViewpagerImgFragment}")
                    Log.e("cyc", "================================================================")
                    viewPagerSetting()
                    chcekViewpager()
                }
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
        viewModel.checkMissWord.observe(this) {
            if (it.isEmpty()) {
                actionSearch()
            } else {
                Toast.makeText(this, "오타를 수정했습니다.", Toast.LENGTH_SHORT).apply {
                    this.setGravity(Gravity.BOTTOM, 0, 100)
                    this.show()
                }
                Log.e("cyc", "오타 수정-->${it}")
                binding.etSearch.setText(it)
                query = it
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
        if(checkViewpagerAllFragment){adapter.addFragment("통합", AllFragment())}
        if(checkViewpagerViewFragment){adapter.addFragment("VIEW", ViewFragment())}
        if(checkViewpagerDicFragment){adapter.addFragment("백과사전", DictionaryFragment())}
        if(checkViewpagerImgFragment){adapter.addFragment("이미지", ImgFragment())}
        binding.vp2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER  //뷰페이저 오버스크롤 없애기
        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tlMenu, binding.vp2) { tab, postion ->
//            tab.text = tabTitle[postion]
            tab.text = adapter.getTitleList(postion)
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
        adapter = ViewpagerFragmentAdapter(this)
        dicEndcheck=false
        imgEndcehck=false
        hideKeyboard()
        if (!query.isNullOrBlank()) {
            saveSearchData(query)
        }
        initViewPagerFragmentCheck()
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

    private fun initViewPagerFragmentCheck() {
        checkViewpagerAllFragment = false
        checkViewpagerViewFragment = false
        checkViewpagerDicFragment = false
        checkViewpagerImgFragment = false
    }

    private fun chcekViewpager() {
        if (!(checkViewpagerAllFragment && checkViewpagerViewFragment)) {
            binding.clSearchResult.visibility = View.INVISIBLE
            binding.tvNoSee.visibility = View.VISIBLE
        } else {
            binding.tvNoSee.visibility = View.INVISIBLE
            binding.clSearchResult.visibility = View.VISIBLE
        }
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




