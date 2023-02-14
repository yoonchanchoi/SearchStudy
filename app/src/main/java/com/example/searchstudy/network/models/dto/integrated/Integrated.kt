package com.example.searchstudy.network.models.dto.integrated

import com.example.searchstudy.network.models.response.AllItems

data class Integrated(
    val title: String ="",
    val allItemsarraylist: ArrayList<AllItems>? = null,
    val type: Int = 1,                                  // 탑입 1 통합 쪽 viewhodler, 2각각의 탭 viewhodler
    val moreLoad: Boolean = false                       // false 더보기 호출, true 일반 호출
)

