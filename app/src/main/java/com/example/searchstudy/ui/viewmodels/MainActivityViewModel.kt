package com.example.searchstudy.ui.viewmodels

import android.util.Log
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



    var blogItemsArraylist = ArrayList<BlogItems>()
    var cafeItemsArraylist = ArrayList<CafeItems>()
    var dictionaryItemsArraylist = ArrayList<DictionaryItems>()
    var imgItemsArraylist = ArrayList<ImgItems>()

    /**
     * search
     */
    fun searchQury(query: String){
        searchBlog(query)
        searCafe(query)
        searchDictionary(query)
        searchImg(query)
    }

    private fun searchBlog(query: String){
        val result = searchManager.searchBlog(query = query)
        result.enqueue(object : Callback<ResultSearchBlog>{
            override fun onResponse(call: Call<ResultSearchBlog>, response: Response<ResultSearchBlog>) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        Log.e("cyc","it.blogItem-->${it.blogItem}")
                        blogItemsArraylist=it.blogItem
                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")
                }
            }
            override fun onFailure(call: Call<ResultSearchBlog>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }

        })
    }

    private fun searCafe(query: String){
        val result = searchManager.searchCafe(query = query)
        result.enqueue(object : Callback<ResultSearchCafe>{
            override fun onResponse(
                call: Call<ResultSearchCafe>,
                response: Response<ResultSearchCafe>
            ) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        Log.e("cyc","it.cafeItem-->${it.cafeItem}")
                       cafeItemsArraylist=it.cafeItem

                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchCafe>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }

    private fun searchDictionary(query: String){
        val result = searchManager.searchDictionary(query = query)
        result.enqueue(object : Callback<ResultSearchDictionary>{
            override fun onResponse(
                call: Call<ResultSearchDictionary>,
                response: Response<ResultSearchDictionary>
            ) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        Log.e("cyc","Mainviewmodel___________________it.dictionaryItem-->${it.dictionaryItem}")
                        dictionaryItemsArraylist=it.dictionaryItem

                    }
                }else{
                    Log.e("cyc", "통신은 성공했지만 해당 통신의 서버에서 내려준 값이 잘못되어 실패")

                }
            }

            override fun onFailure(call: Call<ResultSearchDictionary>, t: Throwable) {
                Log.e("cyc", "통신실패 (인터넷 연결의 문제, 예외발생)")
            }


        })
    }

    private fun searchImg(query: String){
        val result = searchManager.searchImg(query = query)
        result.enqueue(object : Callback<ResultSearchImg>{
            override fun onResponse(
                call: Call<ResultSearchImg>,
                response: Response<ResultSearchImg>
            ) {
                if(response.isSuccessful){
                    Log.e("cyc", "통신 성공")
                    response.body()?.let {
                        Log.e("cyc","it.imgItem-->${it.imgItem}")
                        imgItemsArraylist=it.imgItem

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


}