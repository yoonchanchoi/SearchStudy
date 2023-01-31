package com.example.searchstudy.network.managers
import com.example.searchstudy.network.models.response.ResultSearchBlog
import com.example.searchstudy.network.models.response.ResultSearchCafe
import com.example.searchstudy.network.models.response.ResultSearchDictionary
import com.example.searchstudy.network.models.response.ResultSearchImg
import retrofit2.Call

interface SearchManager {
    fun searchBlog(query: String): Call<ResultSearchBlog>
    fun searchCafe(query: String): Call<ResultSearchCafe>
    fun searchDictionary(query: String): Call<ResultSearchDictionary>
    fun searchImg(query: String): Call<ResultSearchImg>
}