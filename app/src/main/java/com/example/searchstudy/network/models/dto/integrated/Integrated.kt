package com.example.searchstudy.network.models.dto.integrated

import com.example.searchstudy.network.models.response.AllItems

data class Integrated(
    val title: String ="",
    val allItemsarraylist: ArrayList<AllItems>? = null,
    val type: Int = 1,
    val moreLoad: Boolean = false
)

