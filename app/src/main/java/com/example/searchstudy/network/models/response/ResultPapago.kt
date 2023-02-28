package com.example.searchstudy.network.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultPapago(
    @SerializedName("srcLangType")
    @Expose
    val srcLangType: String,

    @SerializedName("tarLangType")
    @Expose
    val tarLangType: String,

    @SerializedName("translatedText")
    @Expose
    val translatedText: String
)
