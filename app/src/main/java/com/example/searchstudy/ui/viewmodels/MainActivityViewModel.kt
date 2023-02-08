package com.example.searchstudy.ui.viewmodels

import android.util.Log
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
    val blogItemsArraylist : LiveData<ArrayList<AllItems>>
        get() = _blogItemsArraylist

    private val _cafeItemsArraylist = MutableLiveData<ArrayList<AllItems>>()
    val cafeItemsArraylist : LiveData<ArrayList<AllItems>>
        get() = _cafeItemsArraylist

    private val _dictionaryItemsArraylist = MutableLiveData<ArrayList<AllItems>>()
    val dictionaryItemsArraylist : LiveData<ArrayList<AllItems>>
        get() = _dictionaryItemsArraylist

    private val _imgItemsArraylist = MutableLiveData<ArrayList<ImgItems>>()
    val imgItemsArraylist : LiveData<ArrayList<ImgItems>>
        get() = _imgItemsArraylist

    private val _allIntegratedArraylist = MutableLiveData<ArrayList<Integrated>>()
    val allIntegratedArraylist : LiveData<ArrayList<Integrated>>
        get()=_allIntegratedArraylist

    private val _viewIntegratedArraylist = MutableLiveData<Integrated>()
    val viewIntegratedArraylist : LiveData<Integrated>
        get()=_viewIntegratedArraylist

    private val _dictionaryIntegratedArraylist = MutableLiveData<Integrated>()
    val dictionaryIntegratedArraylist : LiveData<Integrated>
        get()=_dictionaryIntegratedArraylist

    /**
     * search
     */

    fun searchBlog(query: String){
        val result = searchManager.searchBlog(query = query)
        result.enqueue(object : Callback<ResultSearchAll>{
            override fun onResponse(call: Call<ResultSearchAll>, response: Response<ResultSearchAll>) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        _blogItemsArraylist.postValue(it.allItems)


                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }
            override fun onFailure(call: Call<ResultSearchAll>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }

        })
    }

    fun searchCafe(query: String){
        val result = searchManager.searchCafe(query = query)
        result.enqueue(object : Callback<ResultSearchAll>{
            override fun onResponse(
                call: Call<ResultSearchAll>,
                response: Response<ResultSearchAll>
            ) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        _cafeItemsArraylist.postValue(it.allItems)

                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchAll>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }

    fun searchDictionary(query: String){
        val result = searchManager.searchDictionary(query = query)
        result.enqueue(object : Callback<ResultSearchAll>{
            override fun onResponse(
                call: Call<ResultSearchAll>,
                response: Response<ResultSearchAll>
            ) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        _dictionaryItemsArraylist.postValue(it.allItems)
                        _dictionaryIntegratedArraylist.postValue(Integrated(allItemsarraylist = it.allItems))
                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchAll>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }

    fun searchImg(query: String){
        val result = searchManager.searchImg(query = query)
        result.enqueue(object : Callback<ResultSearchImg>{
            override fun onResponse(
                call: Call<ResultSearchImg>,
                response: Response<ResultSearchImg>
            ) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        _imgItemsArraylist.postValue(it.imgItem)

                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchImg>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }

//    fun setAllitems(allItems: ArrayList<AllItems>){
//        _allItemsArraylist.postValue(allItems)
//    }


    fun setViewitems(viewIntegratedItems: Integrated){

        _viewIntegratedArraylist.postValue(viewIntegratedItems)
    }

    fun setIntegrated(arrayIntegrated: ArrayList<Integrated>){

        _allIntegratedArraylist.postValue(arrayIntegrated)
    }

}