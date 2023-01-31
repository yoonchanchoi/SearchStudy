package com.example.searchstudy.network.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultSearchCafe(
    @SerializedName("lastBuildDate")
    @Expose
    val lastBuildDate: String = "",

    @SerializedName("total")
    @Expose
    val total: Int = 0,

    @SerializedName("start")
    @Expose
    val start: Int = 0,

    @SerializedName("display")
    @Expose
    val display: Int = 0,

    @SerializedName("items")
    @Expose
    val cafeItem: ArrayList<CafeItems>
)

data class CafeItems(
    @SerializedName("title")
    @Expose
    val title: String = "",

    @SerializedName("link")
    @Expose
    val link: String = "",

    @SerializedName("description")
    @Expose
    val description: String = "",

    @SerializedName("cafename")
    @Expose
    val cafename: String = "",

    @SerializedName("cafeurl")
    @Expose
    val cafeurl: String = ""

)


