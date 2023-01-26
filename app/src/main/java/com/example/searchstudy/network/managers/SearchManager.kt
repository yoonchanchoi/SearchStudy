package com.example.searchstudy.network.managers
import retrofit2.Call

interface SearchManager {
    fun searchBlog(): Call<String>
}