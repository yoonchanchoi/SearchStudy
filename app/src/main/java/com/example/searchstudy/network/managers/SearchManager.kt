package com.example.searchstudy.network.managers
import com.example.searchstudy.network.models.request.BlogSearch
import retrofit2.Call

interface SearchManager {
    fun searchBlog(query: String, display:Int, start:Int, sort:String): Call<String>
}