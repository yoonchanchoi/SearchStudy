package com.example.searchstudy.network.models.request

data class DictionarySearch(
    val query: String,
    val display: Int = 10,
    val start: Int = 1
)
