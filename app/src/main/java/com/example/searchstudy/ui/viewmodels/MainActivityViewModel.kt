package com.example.searchstudy.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.searchstudy.network.managers.SearchManager
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val searchManager: SearchManager
) : ViewModel() {


    /**
     * search
     */

    fun searchQury(query: String){
        searchBlog(query)
    }




    private fun searchBlog(query: String){
        val result = searchManager.searchBlog(query = query, display = 10, start = 1, sort = "sim")
        result.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.e("cyc","성공")
                if(response.isSuccessful){
                    Log.e("cyc","성공2")
                    response.body()?.let {
                        Log.e("cyc","response.body()-it--->${it}")
                    }
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("cyc","실패")
            }

        })
    }


}