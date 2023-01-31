package com.example.searchstudy.network.services

import com.example.searchstudy.network.models.response.ResultSearchBlog
import com.example.searchstudy.network.models.response.ResultSearchCafe
import com.example.searchstudy.network.models.response.ResultSearchDictionary
import com.example.searchstudy.network.models.response.ResultSearchImg
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    //나중에 지울것
    //@Field key=value 형식의 데이터를 보낼때 사용
    //@Body는 해당 데이터를 직열화하여 하번에 보내줄때 사용 Json형식일때 사용 한다고 보면 된다. 여기서
    // {
    //  "userId": "pmsdev",
    //  "password": "open1404!!"
    //}

    //이런 형식을 보내준다. 이걸 다시보면 { "userId": "pmsdev", "password": "open1404!!"} 형식의 직열화된 형식으로 보낸다.
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