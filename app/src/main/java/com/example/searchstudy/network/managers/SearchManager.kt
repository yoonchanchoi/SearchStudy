package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import retrofit2.Call

interface SearchManager {
    fun searchBlog(query: String): Call<ResultSearchAll>
    fun searchCafe(query: String): Call<ResultSearchAll>
    fun searchDictionary(query: String): Call<ResultSearchAll>
    fun searchImg(query: String): Call<ResultSearchImg>
}