package com.example.searchstudy.network.managers

import android.view.Display
import com.example.searchstudy.network.models.request.RequestPapago
import com.example.searchstudy.network.models.response.*
import retrofit2.Call

interface SearchManager {
    fun requestBlog(query: String, display: Int, start: Int): Call<ResultSearchAll>
    fun requestCafe(query: String, start: Int): Call<ResultSearchAll>
    fun requestDictionary(query: String, start: Int): Call<ResultSearchAll>
    fun requestImg(query: String, start: Int): Call<ResultSearchImg>
    fun requestCheckAdultWord(query: String): Call<ResultCheckAdultWord>
    fun requestCheckMissWord(query: String): Call<ResultMisspelledWord>
    fun requestPapago(requestPapago: RequestPapago): Call<ResultPapago>
    fun requestNationalLanguage(query: String): Call<ResultNationalLanguage>

}