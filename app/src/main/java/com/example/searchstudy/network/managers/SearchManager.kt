package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import retrofit2.Call

interface SearchManager {
    fun searchBlog(query: String): Call<ResultSearchView>
    fun searchCafe(query: String): Call<ResultSearchView>
    fun searchDictionary(query: String): Call<ResultSearchDictionary>
    fun searchImg(query: String): Call<ResultSearchImg>
}