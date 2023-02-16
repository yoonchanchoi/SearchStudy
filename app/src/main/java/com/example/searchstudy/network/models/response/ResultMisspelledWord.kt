package com.example.searchstudy.network.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultMisspelledWord(
    @SerializedName("errata")
    @Expose
    val errata: String
)
