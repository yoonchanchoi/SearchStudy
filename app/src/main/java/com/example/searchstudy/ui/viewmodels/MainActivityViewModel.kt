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

    private val _blogItemsArraylist = MutableLiveData<ArrayList<AllItems>>()
    val blogItemsArraylist: LiveData<ArrayList<AllItems>>
        get() = _blogItemsArraylist

    private val _cafeItemsArraylist = MutableLiveData<ArrayList<AllItems>>()
    val cafeItemsArraylist: LiveData<ArrayList<AllItems>>
        get() = _cafeItemsArraylist

    private val _dictionaryItemsArraylist = MutableLiveData<ArrayList<AllItems>>()
    val dictionaryItemsArraylist: LiveData<ArrayList<AllItems>>
        get() = _dictionaryItemsArraylist

    private val _imgItemsArraylist = MutableLiveData<ArrayList<ImgItems>>()
    val imgItemsArraylist: LiveData<ArrayList<ImgItems>>
        get() = _imgItemsArraylist

    private val _viewIntegrated = MutableLiveData<Integrated>()
    val viewIntegrated: LiveData<Integrated>
        get() = _viewIntegrated

    private val _dictionaryIntegrated = MutableLiveData<Integrated>()
    val dictionaryIntegrated: LiveData<Integrated>
        get() = _dictionaryIntegrated


    private val _allIntegratedArraylist = MutableLiveData<ArrayList<Integrated>>()
    val allIntegratedArraylist: LiveData<ArrayList<Integrated>>
        get() = _allIntegratedArraylist

    var query = ""
    var lastItemPoint = 0
    var lastViewItemPoint = 0
    var lastDicItemPoint = 0
    var lastImgItemPoint = 0
    var viewMoreLoad = false
    var dicMoreLoad = false
    var imgMoreLoad = false

    /**
     * search
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
                        _blogItemsArraylist.postValue(it.allItems)
                        viewMoreLoad = checkMoreLoad

                    }
                } else {
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }

            override fun onFailure(call: Call<ResultSearchAll>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }

        })
    }

    fun requestCafe(query: String, start: Int = 1) {
        val result = searchManager.requestCafe(query = query, start)
        result.enqueue(object : Callback<ResultSearchAll> {
            override fun onResponse(
                call: Call<ResultSearchAll>,
                response: Response<ResultSearchAll>
            ) {
                if (response.isSuccessful) {
                    Log.e("cyc", "카페-통신 성공")
                    response.body()?.let {
                        _cafeItemsArraylist.postValue(it.allItems)

                    }
                } else {
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchAll>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }

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
                        _dictionaryItemsArraylist.postValue(it.allItems)
                        dicMoreLoad = checkMoreLoad
//                        _dictionaryIntegratedArraylist.postValue(Integrated(allItemsarraylist = it.allItems))
                    }
                } else {
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchAll>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }

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
                        _imgItemsArraylist.postValue(it.imgItem)
                        imgMoreLoad = checkMoreLoad

                    }
                } else {
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchImg>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }


    fun setViewIntegrateditems(viewIntegratedItems: Integrated) {

        _viewIntegrated.postValue(viewIntegratedItems)
    }

    fun setDicIntegrateditems(dicIntegratedItems: Integrated) {

        _dictionaryIntegrated.postValue(dicIntegratedItems)
    }

    fun setAllIntegratedArraylist(arrayListIntegrated: ArrayList<Integrated>) {

        _allIntegratedArraylist.postValue(arrayListIntegrated)
    }
//


}