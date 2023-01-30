package com.example.searchstudy.network.models.request

data class BlogSearch(
    val query: String,
    val display: Int = 10,
    val start: Int = 1,
    val sort: String = "sim"
)
