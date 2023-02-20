package com.example.searchstudy.util

import android.text.Html
import com.example.searchstudy.network.models.response.AllItem

object Util {

    /**
     * 데이터 정렬(한글)
     */
    fun dataSort(arrayAllItems: ArrayList<AllItem>) {
        arrayAllItems.sortWith(compareBy { allItems ->
            val tagExcept = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>"
            val specialChar = "[^\uAC00-\uD7A30-9a-zA-Z\\s]"
            val tagExceptTempStr = allItems.title.replace(Regex(tagExcept), "")
            val specialCharTempStr = tagExceptTempStr.replace(Regex(specialChar), "")
            Html.fromHtml(specialCharTempStr, Html.FROM_HTML_MODE_LEGACY).toString()
        })

    }

    /**
     * 데이터 갯수 세팅
     */
    fun dataExtraction(arrayAllItems: ArrayList<AllItem>): ArrayList<AllItem> {
        val tempArrayList = ArrayList<AllItem>()
        for (i in 0 until arrayAllItems.size) {
            if (i <= 4) {
                tempArrayList.add(arrayAllItems[i])
            }
        }
        return tempArrayList
    }
}