package com.example.searchstudy.ui.viewmodels

import android.util.Log
import android.view.Display
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchstudy.network.managers.SearchManager
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

    //블로그 api response
    private val _blogResultSearchArraylist = MutableLiveData<ResultSearchAll>()
    val blogResultSearchArraylist: LiveData<ResultSearchAll>
        get() = _blogResultSearchArraylist
    //카페 api response
    private val _cafeResultSearchArraylist = MutableLiveData<ResultSearchAll>()
    val cafeResultSearchArraylist: LiveData<ResultSearchAll>
        get() = _cafeResultSearchArraylist
    //백과사전 api response
    private val _dictionaryResultSearchArraylist = MutableLiveData<ResultSearchAll>()
    val dictionaryResultSearchArraylist: LiveData<ResultSearchAll>
        get() = _dictionaryResultSearchArraylist
    //이미지 api response
    private val _imgResultSearchArraylist = MutableLiveData<ResultSearchImg>()
    val imgResultSearchArraylist: LiveData<ResultSearchImg>
        get() = _imgResultSearchArraylist
    //성인검색 단언 api response
    private val _checkAdultWord = MutableLiveData<Int>()
    val checkAdultWord: LiveData<Int>
        get() = _checkAdultWord
    //오타 api response
    private val _checkMissWord = MutableLiveData<String>()
    val checkMissWord: LiveData<String>
        get() = _checkMissWord


    var query = ""                  //검색 입력값
    var viewMoreLoadState = 0       //뷰 더보기 flag
    var blogMoreLoad = false
    var cafeMoreLoad = false
    var dicMoreLoad = false
    var imgMoreLoad = false
//    var lastDicItemPoint = 0
//    var lastImgItemPoint = 0


    /**
     * 블로그 검색 api 통신
     */
    fun requestBlog(query: String, display: Int = 10, start: Int = 1, checkMoreLoad: Boolean = false) {

        val result = searchManager.requestBlog(query = query, display = display, start = start)
        result.enqueue(object : Callback<ResultSearchAll> {
            override fun onResponse(
                call: Call<ResultSearchAll>,
                response: Response<ResultSearchAll>
            ) {
                if (response.isSuccessful) {
                    Log.e("cyc", "블로그-통신 성공")
                    response.body()?.let {
                        _blogResultSearchArraylist.postValue(it)
                        blogMoreLoad = checkMoreLoad
                        Log.e("cyc", "query -> $query, Blog_it.total->${it.total}")
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
                        _cafeResultSearchArraylist.postValue(it)
                        cafeMoreLoad = checkMoreLoad
                        Log.e("cyc", "Cafe_it.total->${it.total}")

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
                        _dictionaryResultSearchArraylist.postValue(it)
                        dicMoreLoad = checkMoreLoad
                        Log.e("cyc", "dic_it.total->${it.total}")

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
                        _imgResultSearchArraylist.postValue(it)
                        imgMoreLoad = checkMoreLoad
                        Log.e("cyc", "img_it.total->${it.total}")


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
                        Log.e("cyc", "성인검색어-- it.adult----->${it.adult}")
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
                        Log.e("cyc", "성인검색어-- it.adult----->${it.errata}")
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
}