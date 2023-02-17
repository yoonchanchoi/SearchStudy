package com.example.searchstudy.ui.viewmodels

import android.util.Log
import android.view.Display
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchstudy.network.managers.SearchManager
import com.example.searchstudy.network.models.dto.integrated.Integrated
import com.example.searchstudy.network.models.response.*
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val searchManager: SearchManager
) : ViewModel() {


    private val _blogItemsArraylist = MutableLiveData<ResultSearchAll>()
    val blogItemsArraylist: LiveData<ResultSearchAll>
        get() = _blogItemsArraylist

    private val _cafeItemsArraylist = MutableLiveData<ResultSearchAll>()
    val cafeItemsArraylist: LiveData<ResultSearchAll>
        get() = _cafeItemsArraylist

    private val _dictionaryItemsArraylist = MutableLiveData<ResultSearchAll>()
    val dictionaryItemsArraylist: LiveData<ResultSearchAll>
        get() = _dictionaryItemsArraylist

    private val _imgItemsArraylist = MutableLiveData<ResultSearchImg>()
    val imgItemsArraylist: LiveData<ResultSearchImg>
        get() = _imgItemsArraylist


//    private val _blogItemsArraylist = MutableLiveData<ArrayList<AllItems>>()
//    val blogItemsArraylist: LiveData<ArrayList<AllItems>>
//        get() = _blogItemsArraylist
//
//    private val _cafeItemsArraylist = MutableLiveData<ArrayList<AllItems>>()
//    val cafeItemsArraylist: LiveData<ArrayList<AllItems>>
//        get() = _cafeItemsArraylist
//
//    private val _dictionaryItemsArraylist = MutableLiveData<ArrayList<AllItems>>()
//    val dictionaryItemsArraylist: LiveData<ArrayList<AllItems>>
//        get() = _dictionaryItemsArraylist
//
//    private val _imgItemsArraylist = MutableLiveData<ArrayList<ImgItems>>()
//    val imgItemsArraylist: LiveData<ArrayList<ImgItems>>
//        get() = _imgItemsArraylist

    private val _viewIntegrated = MutableLiveData<Integrated>()
    val viewIntegrated: LiveData<Integrated>
        get() = _viewIntegrated

    private val _dictionaryIntegrated = MutableLiveData<Integrated>()
    val dictionaryIntegrated: LiveData<Integrated>
        get() = _dictionaryIntegrated

    private val _allIntegratedArraylist = MutableLiveData<ArrayList<Integrated>>()
    val allIntegratedArraylist: LiveData<ArrayList<Integrated>>
        get() = _allIntegratedArraylist

    private val _blogTotalItems = MutableLiveData<Int>()
    val blogTotalItems: LiveData<Int>
        get() = _blogTotalItems

    private val _cafeTotalItems = MutableLiveData<Int>()
    val cafeTotalItems: LiveData<Int>
        get() = _cafeTotalItems

    private val _dicTotalItems = MutableLiveData<Int>()
    val dicTotalItems: LiveData<Int>
        get() = _dicTotalItems

    private val _imgTotalItems = MutableLiveData<Int>()
    val imgTotalItems: LiveData<Int>
        get() = _imgTotalItems

    private val _checkAdultWord = MutableLiveData<Int>()
    val checkAdultWord: LiveData<Int>
        get() = _checkAdultWord

    private val _checkMissWord = MutableLiveData<String>()
    val checkMissWord: LiveData<String>
        get() = _checkMissWord

    //검색 입력값
    var query = ""

    var lastItemPoint = 0
    var lastViewItemPoint = 0
    var lastDicItemPoint = 0
    var lastImgItemPoint = 0
    var viewMoreLoad = false
    var blogMoreLoad = false
    var cafeMoreLoad = false
    var dicMoreLoad = false
    var imgMoreLoad = false


    /**
     * 블로그 검색 api 통신
     */
    fun requestBlog(query: String, start: Int = 1, checkMoreLoad: Boolean = false) {
        val result = searchManager.requestBlog(query = query, start)
        result.enqueue(object : Callback<ResultSearchAll> {
            override fun onResponse(
                call: Call<ResultSearchAll>,
                response: Response<ResultSearchAll>
            ) {
                if (response.isSuccessful) {
                    Log.e("cyc", "블로그-통신 성공")
                    response.body()?.let {
                        _blogItemsArraylist.postValue(it)

//                        _blogItemsArraylist.postValue(it.allItems)
//                        _blogTotalItems.postValue(it.total)
                        blogMoreLoad = checkMoreLoad

                    }
                } else {
                    Log.e("cyc", "블로그-통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }

            override fun onFailure(call: Call<ResultSearchAll>, t: Throwable) {
                Log.e("cyc", "블로그-통신실패 (인터넷 연결의 문제, 예외발생)")
            }

        })
    }
    /**
     * 카페 검색 api 통신
     */
    fun requestCafe(query: String, start: Int = 1, checkMoreLoad: Boolean = false) {
        val result = searchManager.requestCafe(query = query, start)
        result.enqueue(object : Callback<ResultSearchAll> {
            override fun onResponse(
                call: Call<ResultSearchAll>,
                response: Response<ResultSearchAll>
            ) {
                if (response.isSuccessful) {
                    Log.e("cyc", "카페-통신 성공")
                    response.body()?.let {
                        _cafeItemsArraylist.postValue(it)
//                        _cafeItemsArraylist.postValue(it.allItems)
//                        _cafeTotalItems.postValue(it.total)
                        cafeMoreLoad=checkMoreLoad
                    }
                } else {
                    Log.e("cyc", "카페-통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchAll>, t: Throwable) {
                Log.e("cyc", "카페-통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }
    /**
     * 백과사전 검색 api 통신
     */
    fun requestDictionary(query: String, start: Int = 1, checkMoreLoad: Boolean = false) {
        val result = searchManager.requestDictionary(query = query, start)
        result.enqueue(object : Callback<ResultSearchAll> {
            override fun onResponse(
                call: Call<ResultSearchAll>,
                response: Response<ResultSearchAll>
            ) {
                if (response.isSuccessful) {
                    Log.e("cyc", "백과사전-통신 성공")
                    response.body()?.let {
                        _dictionaryItemsArraylist.postValue(it)
//                        _dictionaryItemsArraylist.postValue(it.allItems)
//                        _dicTotalItems.postValue(it.total)
                        dicMoreLoad = checkMoreLoad
//                        _dictionaryIntegratedArraylist.postValue(Integrated(allItemsarraylist = it.allItems))
                    }
                } else {
                    Log.e("cyc", "백과사전-통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchAll>, t: Throwable) {
                Log.e("cyc", "백과사전-통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }
    /**
     * 이미지 검색 api 통신
     */
    fun requestImg(query: String, start: Int = 1, checkMoreLoad: Boolean = false) {
        val result = searchManager.requestImg(query = query, start)
        result.enqueue(object : Callback<ResultSearchImg> {
            override fun onResponse(
                call: Call<ResultSearchImg>,
                response: Response<ResultSearchImg>
            ) {
                if (response.isSuccessful) {
                    Log.e("cyc", "이미지-통신 성공")
                    response.body()?.let {
                        _imgItemsArraylist.postValue(it)
//                        _imgItemsArraylist.postValue(it.imgItem)
//                        _imgTotalItems.postValue(it.total)
                        imgMoreLoad = checkMoreLoad

                    }
                } else {
                    Log.e("cyc", "이미지-통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchImg>, t: Throwable) {
                Log.e("cyc", "이미지-통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }
    /**
     * 성인단어 필터 api 통신
     */
    fun requestCheckAdultWord(query: String) {
        val result = searchManager.requestCheckAdultWord(query = query)
        result.enqueue(object : Callback<ResultCheckAdultWord> {
            override fun onResponse(
                call: Call<ResultCheckAdultWord>,
                response: Response<ResultCheckAdultWord>
            ) {
                if (response.isSuccessful) {
                    Log.e("cyc", "성인-문자열-통신 성공")
                    response.body()?.let {
                        _checkAdultWord.postValue(it.adult)
                        Log.e("cyc","성인검색어-- it.adult----->${it.adult}")
                    }
                } else {
                    Log.e("cyc", "성인 통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultCheckAdultWord>, t: Throwable) {
                Log.e("cyc", "성인-통신실패 (인터넷 연결의 문제, 예외발생)")

            }


        })
    }
    /**
     * 오타 단언 필터 api 통신
     */
    fun requestCheckMissWord(query: String) {
        val result = searchManager.requestCheckMissWord(query = query)
        result.enqueue(object : Callback<ResultMisspelledWord> {
            override fun onResponse(
                call: Call<ResultMisspelledWord>,
                response: Response<ResultMisspelledWord>
            ) {
                if (response.isSuccessful) {
                    Log.e("cyc", "성인-문자열-통신 성공")
                    response.body()?.let {
                        _checkMissWord.postValue(it.errata)
                        Log.e("cyc","성인검색어-- it.adult----->${it.errata}")
                    }
                } else {
                    Log.e("cyc", "성인 통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultMisspelledWord>, t: Throwable) {
                Log.e("cyc", "성인-통신실패 (인터넷 연결의 문제, 예외발생)")

            }


        })
    }

    /**
     * view 아이템 세팅
     */
    fun setViewIntegrateditems(viewIntegratedItems: Integrated) {
        _viewIntegrated.postValue(viewIntegratedItems)
    }
    /**
     * 백과사전 아이템 세팅
     */
    fun setDicIntegrateditems(dicIntegratedItems: Integrated) {

        _dictionaryIntegrated.postValue(dicIntegratedItems)
    }

    /**
     * 통합 아이템 세팅
     */
    fun setAllIntegratedArraylist(arrayListIntegrated: ArrayList<Integrated>) {

        _allIntegratedArraylist.postValue(arrayListIntegrated)
    }
//


}