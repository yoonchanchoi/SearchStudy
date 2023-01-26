package com.example.searchstudy.network.managers

import com.example.searchstudy.network.services.SearchService
import retrofit2.Call
import javax.inject.Inject

class SearchManagerImpl @Inject constructor(private val service: SearchService): SearchManager{
    override fun searchBlog(): Call<String> =
        service.searchBlog()
}

