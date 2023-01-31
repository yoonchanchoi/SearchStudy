package com.example.searchstudy.network.services

import com.example.searchstudy.network.models.response.ResultSearchBlog
import com.example.searchstudy.network.models.response.ResultSearchCafe
import com.example.searchstudy.network.models.response.ResultSearchDictionary
import com.example.searchstudy.network.models.response.ResultSearchImg
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/v1/search/blog.json")
    fun searchBlog(
        @Query(value = "query", encoded = true) query: String,
        @Query(value = "display") display: Int? = null,
        @Query(value = "start") start: Int? = null,
        @Query(value = "sort") sort: String? = null
    ): Call<ResultSearchBlog>


    @GET("/v1/search/cafearticle.json")
    fun searchCafe(
        @Query(value = "query", encoded = true) query: String,
        @Query(value = "display") display: Int? = null,
        @Query(value = "start") start: Int? = null,
        @Query(value = "sort") sort: String? = null
    ): Call<ResultSearchCafe>

    @GET("/v1/search/encyc.json")
    fun searchDictionary(
        @Query(value = "query", encoded = true) query: String,
        @Query(value = "display") display: Int? = null,
        @Query(value = "start") start: Int? = null,
    ): Call<ResultSearchDictionary>

    @GET("/v1/search/image")
    fun searchImg(
        @Query(value = "query", encoded = true) query: String,
        @Query(value = "display") display: Int? = null,
        @Query(value = "start") start: Int? = null,
        @Query(value = "sort") sort: String? = null,
        @Query(value = "filter") filter: String? = null
    ): Call<ResultSearchImg>

}