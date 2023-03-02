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
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchstudy.R
import com.example.searchstudy.databinding.ActivityMainBinding
import com.example.searchstudy.network.models.dto.searchDto.SearchData
import com.example.searchstudy.ui.dialog.AdultWarningDialogFragment
import com.example.searchstudy.ui.dialog.LoadingProgressDialog
import com.example.searchstudy.ui.fragment.AllFragment
import com.example.searchstudy.ui.fragment.DictionaryFragment
import com.example.searchstudy.ui.fragment.ImgFragment
import com.example.searchstudy.ui.fragment.ViewFragment
import com.example.searchstudy.ui.recyclerview.search.SearchAdapter
import com.example.searchstudy.ui.recyclerview.search.SearchRecyclerListener
import com.example.searchstudy.ui.recyclerview.viewpager.ViewpagerFragmentAdapter
import com.example.searchstudy.ui.viewmodels.MainActivityViewModel
import com.example.searchstudy.util.*
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.collections.ArrayList


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchRecyclerListener {

    @Inject
    lateinit var pref: Pref

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private lateinit var searchDataList: ArrayList<SearchData>
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var viewPagerAdapter: ViewpagerFragmentAdapter
    private lateinit var loadingProgressDialog: LoadingProgressDialog

    private var query = ""  // 검색어
    private var preQuery = "" // 이전 검색어
    private var dicEndcheck = false
    private var imgEndcehck = false
    private var checkViewpagerViewFragment = false
    private var checkViewpagerDicFragment = false
    private var checkViewpagerImgFragment = false
    private var waitTime = 0L
    private val fragments: ArrayList<Fragment> = arrayListOf()
    private val titles: ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //data 바인도 있음(현재는 뷰 바인딩)
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
        loadingProgressDialog = LoadingProgressDialog(this)
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
        //블로그 요청의 결과 값 옵저버
        viewModel.blogResultSearchArraylist.observe(this) {
            if (!viewModel.blogMoreLoad) {
                if (it.allItems.isNotEmpty()) {
                    checkViewpagerViewFragment = true
                }
                viewModel.requestCafe(query)
            }
        }

        //카페 요청의 결과 값 옵저버
        viewModel.cafeResultSearchArraylist.observe(this) {

            if (!viewModel.cafeMoreLoad) {
//                it.allItems.isNotEmpty()
                if (it.allItems.isNotEmpty()) {
                    checkViewpagerViewFragment = true
                }
                viewModel.requestDictionary(query)
            }
        }

        //백과사전 요청의 결과 값 옵저버
        viewModel.dictionaryResultSearchArraylist.observe(this) {
            if (!viewModel.dicMoreLoad) {
                if (it.allItems.isNotEmpty()) {
                    checkViewpagerDicFragment = true
                }
                dicEndcheck = true
                if (dicEndcheck && imgEndcehck) {

                    viewPagerSetting()
                }
            }
        }
        //이미지 요청의 결과 값 옵저버
        viewModel.imgResultSearchArraylist.observe(this) {
            if (!viewModel.imgMoreLoad) {
                imgEndcehck = true
                if (it.imgItems.isNotEmpty()) {
                    checkViewpagerImgFragment = true
                }
                if (dicEndcheck && imgEndcehck) {
                    viewPagerSetting()
                }
            }
        }

        //성인 검색어 체크 옵저버
        viewModel.checkAdultWord.observe(this) {
            if (it == Constants.MISSWORD) {
                AdultWarningDialogFragment().show(supportFragmentManager, "AdultWarningDialog")
                binding.etSearch.text.clear()
            } else {
                viewModel.requestCheckMissWord(query)
            }
        }
        //오타 검색어 체크 옵저버
        viewModel.checkMissWord.observe(this) {
            if (it.isEmpty()) {
                actionSearch()
            } else {
                Toast.makeText(this, getString(R.string.miss_word_correction), Toast.LENGTH_SHORT)
                    .apply {
                        this.setGravity(Gravity.BOTTOM, 0, 100)
                        this.show()
                    }
                binding.etSearch.setText(it)
                query = it
                actionSearch()
            }
        }
        viewModel.nationalLanguageResult.observe(this) {
            val keyword = query.replace(getString(R.string.translation), "")
            var temp = getString(R.string.korean)
            if (it.langCode == getString(R.string.korean)) {
                temp = getString(R.string.english)
            }
            Log.e("cyc","---------------------------nationalLanguageResult---------------------------")

            viewModel.requestPapago(it.langCode, temp, keyword)


        }
    }

    /**
     * 최근 검색어에 데이터의 유무에 따른 뷰 보여주기
     */
    private fun checkSearchTextData() {
        binding.tvSearchEmpty.visibility =
            if (searchAdapter.itemCount > 0) View.INVISIBLE else View.VISIBLE

//      해당 아래 코드가 위처럼 줄일 수 있음
//        if (searchAdapter.itemCount > 0) {
//            binding.tvSearchEmpty.visibility = View.INVISIBLE
//        } else {
//            binding.tvSearchEmpty.visibility = View.VISIBLE
//        }
    }


    /**
     * 최근 검색어에 저장
     */
    private fun saveSearchData(searchTerm: String) {

        searchDataList.removeIf {
            it.searchText == searchTerm
        }

        // 새 아이템 넣기
        val newSearchData =
            SearchData(searchText = searchTerm, searchTime = System.currentTimeMillis().toString())
        this.searchDataList.add(newSearchData)

        if (searchDataList.size > 10) {
            searchDataList.removeAt(0)
        }

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
        checkAdultWord()
    }


    /**
     * 뷰페이저 세팅
     */
    private fun viewPagerSetting() {
        viewPagerAdapter = ViewpagerFragmentAdapter(this)
        if (checkViewpagerViewFragment) {
            fragments.add(ViewFragment())
            titles.add(getString(R.string.tab_title_view))
        }

        if (checkViewpagerDicFragment) {
            fragments.add(DictionaryFragment())
            titles.add(getString(R.string.tab_title_dictionary))
        }

        if (checkViewpagerImgFragment) {
            fragments.add(ImgFragment())
            titles.add(getString(R.string.tab_title_img))
        }

        if (fragments.size > 0 && fragments[0] !is ImgFragment) {
            fragments.add(0, AllFragment())
            titles.add(0, getString(R.string.tab_title_all))
        }

        binding.vp2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER  //뷰페이저 오버스크롤 없애기
        viewPagerAdapter.setFragment(fragments)
        binding.vp2.adapter = viewPagerAdapter
//        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tlMenu, binding.vp2) { tab, postion ->
            tab.text = titles[postion]
        }.attach()

        //어댑터의 아이템 갯수에 따른 화면 보여주기
        if (fragments.size > 0) {
            binding.tvNoSee.visibility = View.INVISIBLE
            binding.clSearchResult.visibility = View.VISIBLE
            saveSearchData(query)
            searchAdapter.notifyDataSetChanged()
        } else {
            binding.clSearchResult.visibility = View.INVISIBLE
            binding.tvNoSee.visibility = View.VISIBLE
        }
        loadingProgressDialog.dismiss()
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

        initViewPagerFragmentFlag()
        loadingProgressDialog.show()
        Log.e("cyc","")
        Log.e("cyc","----------------------------------------------------------------")
        Log.e("cyc","checkPapago(query)-------action-------->${checkPapago(query)}")
        Log.e("cyc","----------------------------------------------------------------")
        Log.e("cyc","")
        if (checkPapago(query)) {
//            val tempStr = query.apply {
//                this.replace(getString(R.string.translation), "")
//                this.replace(" ","")
//            }
//            Log.e("cyc","tempStr ---> ${tempStr}")
//            Log.e("cyc","query.replace(getString(R.string.translation)).trim()-->${query.replace(getString(R.string.translation), "").trim()}")
//            Log.e("cyc","공백 제거-->${query.apply {
//                this.replace(getString(R.string.translation), "")
//                this.replace(" ","")
//            }}")

            viewModel.requestNationalLanguage(query.apply {
                this.replace(getString(R.string.translation), "")
                this.replace(" ","")
            })
        }
        dicEndcheck = false
        imgEndcehck = false
        hideKeyboard()
        viewModel.requestBlog(query = query)
        viewModel.requestImg(query = query)
        viewModel.query = query
//        adapter.notifyDataSetChanged()
        binding.clSearch.visibility = View.INVISIBLE
        binding.clSearchResult.visibility = View.VISIBLE
        binding.etSearch.clearFocus()
    }


    /**
     * 성인 검색어 체크
     */
    private fun checkAdultWord() {
        query = binding.etSearch.text.toString()
        if (preQuery != query && !query.isNullOrBlank()) {
            preQuery = query
            viewModel.requestCheckAdultWord(query)
        }
    }

    /**
     * 뷰페이저 관련 프래그먼트 및 체크 초기화
     */
    private fun initViewPagerFragmentFlag() {
        checkViewpagerViewFragment = false
        checkViewpagerDicFragment = false
        checkViewpagerImgFragment = false
        fragments.clear()
        titles.clear()
    }


    /**
     * 뒤로 가기 버튼 클릭
     */
    override fun onBackPressed() {
        if (binding.clSearchResult.isVisible or binding.tvNoSee.isVisible) {
            binding.clSearchResult.visibility = View.INVISIBLE
            binding.clSearch.visibility = View.VISIBLE
            binding.tvNoSee.visibility = View.INVISIBLE
            binding.etSearch.text.clear()
            preQuery = ""
            checkSearchTextData()
        } else {
            if (System.currentTimeMillis() - waitTime >= 1500) {
                waitTime = System.currentTimeMillis()
                Toast.makeText(this, getString(R.string.app_finish_toast), Toast.LENGTH_SHORT)
                    .show()
            } else {
                finish() // 액티비티 종료
            }
        }
    }
    /**
     * 단어 번역 체크
     */
    private fun checkPapago(query: String): Boolean {
        Log.e("cyc","")
        Log.e("cyc","----------------------------------------------------------------")
        Log.e("cyc","-----------------------checkPapago(query)-----------------------")
        Log.e("cyc","query.length--->${query.length}")
        Log.e("cyc","----------------------------------------------------------------")
        Log.e("cyc","")
        if (query.length >= 3) {
            Log.e("cyc","query.contains(getString(R.string.translation)--->${query.contains(getString(R.string.translation))}")

            if (query.contains(getString(R.string.translation))) {
                Log.e("cyc","")
                Log.e("cyc","----------------------------------------------------------------")
                Log.e("cyc","(query.substring(0, 2)--->${(query.substring(0, 2))}")

                Log.e("cyc","(query.substring(0, 2) == getString(R.string.translation) )--->${(query.substring(0, 2) == getString(R.string.translation) )}")
                Log.e("cyc","----------------------------------------------------------------")
                Log.e("cyc","")

                Log.e("cyc","=========================================================================")

                Log.e("cyc","")
                Log.e("cyc","----------------------------------------------------------------")
                Log.e("cyc","(query.substring(query.length - 2, query.length))--->${(query.substring(query.length - 2, query.length))}")

                Log.e("cyc","(query.substring(query.length - 2, query.length) == getString(R.string.translation))--->${(query.substring(query.length - 2, query.length) == getString(R.string.translation))}")
                Log.e("cyc","----------------------------------------------------------------")
                Log.e("cyc","")
                if ((query.substring(0, 2) == getString(R.string.translation)) || (query.substring(query.length - 2, query.length) == getString(R.string.translation))) {
                    return true
                }
            }
        }
        return false
    }

}


//        if (endSearchFlag) {
//            initViewPagerFragmentCheck()
//            endSearchFlag = false
//            dicEndcheck = false
//            imgEndcehck = false
//            adapter = ViewpagerFragmentAdapter(this)
//            hideKeyboard()
//            if (!query.isNullOrBlank()) {
//                saveSearchData(query)
//            }
////        binding.btnSearch.isEnabled=false
//            viewModel.requestBlog(query = query)
//            viewModel.requestImg(query = query)
//            viewModel.query = query
//            searchAdapter.notifyDataSetChanged()
////            adapter.notifyDataSetChanged()
//            binding.clSearch.visibility = View.INVISIBLE
//            binding.clSearchResult.visibility = View.VISIBLE
//            binding.etSearch.clearFocus()
//
//        }


//    private fun chcekViewpager() {
//        if (!(checkViewpagerAllFragment && checkViewpagerViewFragment)) {
//            binding.clSearchResult.visibility = View.INVISIBLE
//            binding.tvNoSee.visibility = View.VISIBLE
//        } else {
//            binding.tvNoSee.visibility = View.INVISIBLE
//            binding.clSearchResult.visibility = View.VISIBLE
//        }
//    }


// list 검색단어 포함여부 체크
// 검색단어 포함 YES
// --> 검색단어 삭제
// --> 최상단 추가
// 검색단어 포함 NO
// --> 최상단 추가


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


/*
* blog -> cafe -> dic -> img
* blog = size > 0 add
* cafe = b_size <= 0 && size > 0 add
* dic = size > 0 add
* img = size > 0 add
* All = !frag.isEmpty = add(index=0)
*
* */

