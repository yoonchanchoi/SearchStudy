package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import com.example.searchstudy.network.services.SearchService
import retrofit2.Call
import javax.inject.Inject

class SearchManagerImpl @Inject constructor(private val service: SearchService) : SearchManager {
    override fun searchBlog(query: String): Call<ResultSearchAll> =
        service.searchBlog(query)

    override fun searchCafe(query: String): Call<ResultSearchAll> =
        service.searchCafe(query)

    override fun searchDictionary(query: String): Call<ResultSearchAll> =
        service.searchDictionary(query)

    override fun searchImg(query: String): Call<ResultSearchImg> =
        service.searchImg(query)

}

