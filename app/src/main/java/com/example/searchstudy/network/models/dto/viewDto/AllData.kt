package com.example.searchstudy.network.models.dto.viewDto

import com.example.searchstudy.network.models.response.AllItems

data class AllData(
    val title: String,
    val item: ArrayList<AllItems>
)
