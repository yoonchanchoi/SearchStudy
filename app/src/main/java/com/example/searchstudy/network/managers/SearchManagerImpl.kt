package com.example.searchstudy.network.managers

import com.example.searchstudy.network.models.response.*
import com.example.searchstudy.network.services.SearchService
import retrofit2.Call
import javax.inject.Inject

class SearchManagerImpl @Inject constructor(private val service: SearchService) : SearchManager {
    override fun requestBlog(query: String, start: Int): Call<ResultSearchAll> =
        service.requestBlog(query, start = start)

    override fun requestCafe(query: String, start: Int): Call<ResultSearchAll> =
        service.requestCafe(query, start = start)

    override fun requestDictionary(query: String, start: Int): Call<ResultSearchAll> =
        service.requestDictionary(query, start = start)

    override fun requestImg(query: String, start: Int): Call<ResultSearchImg> =
        service.requestImg(query, start = start)

}

