package com.example.searchstudy.network.models.dto.viewDto

import okhttp3.Interceptor

data class ViewData<T>(
    val title: String,
    val items: ArrayList<T>
)
