package com.example.searchstudy.network.models.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestPapago(
    // 원본 언어
    val source: String,
    // 타겟 언어
    val target: String,
    // 번역 텍스트
    val text: String
)
