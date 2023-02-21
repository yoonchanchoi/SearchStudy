package com.example.searchstudy.network.services

import com.example.searchstudy.network.models.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("/v1/search/blog.json")
    fun requestBlog(
        @Query(value = "query", encoded = true) query: String,
        @Query(value = "display") display: Int,
        @Query(value = "start") start: Int? = null,
        @Query(value = "sort") sort: String? = null
    ): Call<ResultSearchAll>


    @GET("/v1/search/cafearticle.json")
    fun requestCafe(
        @Query(value = "query", encoded = true) query: String,
        @Query(value = "display") display: Int? = 100,
        @Query(value = "start") start: Int? = null,
        @Query(value = "sort") sort: String? = null
    ): Call<ResultSearchAll>

    @GET("/v1/search/encyc.json")
    fun requestDictionary(
        @Query(value = "query", encoded = true) query: String,
        @Query(value = "display") display: Int? = 10,
        @Query(value = "start") start: Int? = null
    ): Call<ResultSearchAll>

    @GET("/v1/search/image")
    fun requestImg(
        @Query(value = "query", encoded = true) query: String,
        @Query(value = "display") display: Int? = 20,
        @Query(value = "start") start: Int? = null,
        @Query(value = "sort") sort: String? = null,
        @Query(value = "filter") filter: String? = null
    ): Call<ResultSearchImg>


    @GET("/v1/search/adult.json")
    fun requestCheckAdultWord(
        @Query(value = "query", encoded = true) query: String): Call<ResultCheckAdultWord>

    @GET("/v1/search/errata.json")
    fun requestCheckMissWord(
        @Query(value = "query", encoded = true) query: String): Call<ResultMisspelledWord>


}