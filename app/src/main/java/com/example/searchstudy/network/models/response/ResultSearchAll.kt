package com.example.searchstudy.network.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultSearchAll(
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
    val allItems: ArrayList<AllItems>
)

data class AllItems(
    @SerializedName("title")
    @Expose
    val title: String = "",

    @SerializedName("link")
    @Expose
    val link: String = "",

    @SerializedName("description")
    @Expose
    val description: String = "",

    @SerializedName("bloggername")
    @Expose
    val bloggername: String = "",

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String = "",

    @SerializedName("bloggerlink")
    @Expose
    val bloggerlink: String = "",

    @SerializedName("postdate")
    @Expose
    val postdate: String = "",

    @SerializedName("cafename")
    @Expose
    val cafename: String = "",

    @SerializedName("cafeurl")
    @Expose
    val cafeurl: String = "",
)
